package com.bravewood.safelenderbatchprocessing.payroll.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long GrossEarnings;
    private Long GrossDeductions;
    private Long NetPay;
    @ManyToOne
    private PayrollGroup payrollGroup;



}
