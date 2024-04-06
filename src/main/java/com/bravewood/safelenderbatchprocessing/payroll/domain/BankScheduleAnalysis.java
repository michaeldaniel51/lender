package com.bravewood.safelenderbatchprocessing.payroll.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "bank_schedule_analysis")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankScheduleAnalysis {

    @Id
    @GeneratedValue
    private Long id;
    private Long monthInView;
    private BigDecimal minNetPay;
    private BigDecimal minGrossPay;
    private String preferredPaymentMode;
    private Long minNumberOfSalaryPayment;
    private BigDecimal DSR;
    private Boolean allowNull;
    @ManyToOne
    private LoanDecider loanDecider;



}
