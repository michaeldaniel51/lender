package com.bravewood.safelenderbatchprocessing.payroll.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "PAYROLL_CUSTOMER")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payroll {

    @Id
    @GeneratedValue
    private Long id;
    private Long EmployeeNumber;
    private String FullName;
    private String Department;
    private String Ministry;
    private String BankName;
    private Long BankCode;
    private Long AccountNo;
    private String GradeLevel;
    private BigDecimal GrossEarnings;
    private BigDecimal GrossDeductions;
    private BigDecimal NetPay;
    @ManyToOne
    private PayrollGroup payrollGroup;
    private Long status;
    private String message;



}
