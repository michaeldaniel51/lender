package com.bravewood.safelenderbatchprocessing.payroll.api;


import com.bravewood.safelenderbatchprocessing.config.ApiConstants;
import com.bravewood.safelenderbatchprocessing.payroll.domain.Payroll;
import com.bravewood.safelenderbatchprocessing.payroll.domain.PayrollGroup;
import com.bravewood.safelenderbatchprocessing.payroll.repository.PayrollGroupRepo;
import com.bravewood.safelenderbatchprocessing.payroll.service.PayrollService;
import com.bravewood.safelenderbatchprocessing.payroll.service.PayrollServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import static com.bravewood.safelenderbatchprocessing.config.ApiConstants.PAYROLL;
import static com.bravewood.safelenderbatchprocessing.config.ApiConstants.PREQUALIFICATION;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(PAYROLL)
public class PayrollApi {


    @Autowired
    private final PayrollServiceImpl payrollServiceImpl;

    private final PayrollService payrollService;




    @PostMapping("/download_excel")
    public ResponseEntity<?> downloadExcel() {
        ByteArrayResource resource = payrollServiceImpl.generateExcel();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=payroll"+generateUniqueId()+".xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(resource.contentLength())
                    .body(resource);

    }


    @PostMapping("/download")
    public void downloadCsv(HttpServletResponse response) {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=payroll_" + generateUniqueId() + ".csv";
        response.setHeader(headerKey, headerValue);

        payrollServiceImpl.downloadCsvFile(response);
        }


    private static String generateUniqueId() {
        final Long time = System.currentTimeMillis();
        final String uniqueVal = String.valueOf(time);
        return Long.toHexString(Long.parseLong(uniqueVal));
    }



    @PostMapping("/upload")
    public BatchStatus uploadBatch(@RequestParam("file") MultipartFile multipartFile, @RequestParam Long productId, @RequestParam Long employerId, @RequestParam String name,@RequestParam String user_email, @RequestParam YearMonth uploaded_period,@RequestParam String message ) {

        return payrollService.uploadByBatch(multipartFile,productId,employerId,name,user_email,uploaded_period,message);

    }

    @PostMapping("/payroll_group_list")
    public List<PayrollGroup> findByUploadedDateAndStatus(@RequestParam LocalDate uploaded_date, @RequestParam Integer status) {

        return payrollServiceImpl.findByUploadedDateAndStatus(uploaded_date,status);

    }

    @PostMapping("/payroll_customer_list/{payrollGroupId}")
    public List<Payroll> findByPayrollGroupAndStatus(@PathVariable Long payrollGroupId,@RequestParam Long status) {

        return payrollServiceImpl.findByPayrollGroupAndStatus(payrollGroupId,status);

    }

}
