package com.bravewood.safelenderbatchprocessing.payroll.service;


import com.bravewood.safelenderbatchprocessing.payroll.domain.Payroll;
import com.bravewood.safelenderbatchprocessing.payroll.domain.PayrollGroup;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.batch.core.BatchStatus;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

public interface PayrollService {


     ByteArrayResource generateExcel();

     void downloadCsvFile(HttpServletResponse response);

     BatchStatus uploadByBatch(MultipartFile multipartFile, Long productId, Long employerId, String name,String user_email, YearMonth uploaded_period,String message);

     List<PayrollGroup> findByUploadedDateAndStatus(LocalDate uploadedDate,Integer status);

     List<Payroll> findByPayrollGroupAndStatus(Long payrollGroupId,Long status);



}
