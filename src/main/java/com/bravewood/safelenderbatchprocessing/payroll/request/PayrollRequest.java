package com.bravewood.safelenderbatchprocessing.payroll.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class PayrollRequest {


    private String employeeNumber;
    private String FullName;
    private String ministry;
    private String gradeLevel;
    private String Department;
    private String bankName;
    private String bankCode;
    private String accountNo;
    private String grossEarnings;
    private String grossDeductions;
    private String netPay;








}
