package com.bravewood.safelenderbatchprocessing.payroll.service;

import com.bravewood.safelenderbatchprocessing.payroll.domain.FileDb;
import com.bravewood.safelenderbatchprocessing.payroll.domain.Payroll;
import com.bravewood.safelenderbatchprocessing.payroll.domain.PayrollGroup;
import com.bravewood.safelenderbatchprocessing.payroll.exception.PayrollException;
import com.bravewood.safelenderbatchprocessing.payroll.repository.PayrollGroupRepo;
import com.bravewood.safelenderbatchprocessing.payroll.repository.PayrollRepo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PayrollServiceImpl implements PayrollService {

    private final JobLauncher jobLauncher;
    private final Job job;
    private final FileDbService storageService;
    private final PayrollGroupRepo payrollGroupRepo;
    private final PayrollRepo payrollRepo;

    @Value("${batch.temporary.storage.url}")
    private String TEMP_STORAGE;

    public ByteArrayResource generateExcel() {

        try {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Payroll Report");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("EmployeeNumber");
            headerRow.createCell(1).setCellValue("FullName");
            headerRow.createCell(2).setCellValue("Department");
            headerRow.createCell(3).setCellValue("Ministry");
            headerRow.createCell(4).setCellValue("BankName");
            headerRow.createCell(5).setCellValue("BankCode");
            headerRow.createCell(6).setCellValue("AccountNo");
            headerRow.createCell(7).setCellValue("GradeLevel");
            headerRow.createCell(8).setCellValue("GrossEarnings");
            headerRow.createCell(9).setCellValue("GrossDeductions");
            headerRow.createCell(10).setCellValue("NetPay");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            byte[] excelBytes = outputStream.toByteArray();

            ByteArrayResource resource = new ByteArrayResource(excelBytes);

            return resource;

        } catch (Exception e) {
            throw new PayrollException("data does not exist", e);

        }

    }


    @Override
    public void downloadCsvFile(HttpServletResponse response) {

        try{

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"EmployeeNumber",
                "FullName",
                "Department",
                "Ministry",
                "BankName",
                "BankCode",
                "AccountNo",
                "GradeLevel",
                "GrossEarnings",
                "GrossDeductions",
                "NetPay"};

        csvWriter.writeHeader(csvHeader);
        csvWriter.close();

    }catch (IOException e){
            throw new PayrollException("error downloading the files",e);
        }

}

    @Override
    public BatchStatus uploadByBatch(MultipartFile multipartFile, Long productId, Long employerId, String name,String user_email,YearMonth uploaded_period,String message) {

            try {

                if (Objects.equals(multipartFile.getContentType(), "text/csv")) {

                   PayrollGroup payrollGroup = new PayrollGroup();

                   payrollGroup.setStatus(100);

                    if (BatchStatus.COMPLETED.ordinal()==0){
                        payrollGroup.setStatus(200);
                    }else if (BatchStatus.STARTING.ordinal()==1){
                        payrollGroup.setStatus(300);
                    }else if (BatchStatus.STOPPED.ordinal()==4){
                        payrollGroup.setStatus(400);
                    }else if (BatchStatus.FAILED.ordinal()==5){
                        payrollGroup.setStatus(600);
                        throw new PayrollException("file was not uploaded");
                    }

                    payrollGroup.setEmployerId(employerId);
                    payrollGroup.setName(name);
                    payrollGroup.setProductId(productId);
                    payrollGroup.setUpload_period(uploaded_period.toString());

                    if (!user_email.isEmpty()){
                        payrollGroup.setUser_email(user_email);
                    }else {
                        throw new PayrollException("email cannot be empty");
                    }

                   PayrollGroup payrollGroupId = payrollGroupRepo.save(payrollGroup);

                   FileDb fileDb = storageService.storeFile(multipartFile);

                   fileDb.setPayrollGroup(payrollGroupId);

                    String originalFileName = multipartFile.getOriginalFilename();
                    File fileToImport = new File(TEMP_STORAGE + originalFileName);
                    multipartFile.transferTo(fileToImport);

                    JobParameters jobParameters = new JobParametersBuilder()
                            .addLong("status", Long.valueOf(payrollGroup.getStatus()))
                            .addString("message", message)
                        .addLong("payrollGroupId", payrollGroupId.getId())
                        .addString("fullPathFileName", TEMP_STORAGE + originalFileName)
                        .addLong("startAt", System.currentTimeMillis()).toJobParameters();

                JobExecution run = jobLauncher.run(job, jobParameters);

                    if (payrollGroupId.getStatus()== 200){
                        Files.deleteIfExists(Paths.get(TEMP_STORAGE+originalFileName));
               }

                return run.getStatus();
                }else {
                    throw new PayrollException("file must not be empty and must be a csv file");
                }

            } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException | IOException |
                 JobParametersInvalidException | JobRestartException | PayrollException e ) {

            throw new PayrollException(e,"error connecting,Please contact the customer service center");

        }

    }



    public List<PayrollGroup> findByUploadedDateAndStatus(LocalDate uploaded_date,Integer status){

        List<PayrollGroup> findPayrollGroup = payrollGroupRepo.findAll().stream().filter(payrollGroup -> payrollGroup.getUploaded_date().equals(uploaded_date) && payrollGroup.getStatus().equals(status)).collect(Collectors.toList());

        if (findPayrollGroup.isEmpty()) {
        throw new PayrollException("found no result");
        }

       return findPayrollGroup;

    }

    @Override
    public List<Payroll> findByPayrollGroupAndStatus(Long payrollGroupId,Long status) {


        List<Payroll> payrollGroup = payrollRepo.findAll().stream().filter(payroll -> payroll.getPayrollGroup().getId().equals(payrollGroupId)).toList();

        if (payrollGroup.isEmpty()) {
            throw new PayrollException("found no result");
        }

        return payrollGroup;


    }


}


