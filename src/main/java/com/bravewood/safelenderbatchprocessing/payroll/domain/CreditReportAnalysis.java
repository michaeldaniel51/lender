package com.bravewood.safelenderbatchprocessing.payroll.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "credit_report_analysis")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditReportAnalysis {


    @Id
    @GeneratedValue
    private Long id;
    private Long maximumMonthInArrears;
    private BigDecimal maximumLoanAmount;
    private BigDecimal monthlyDSR;
    private String categoryName;
    private BigDecimal interestRate;
    @ManyToOne
    private LoanDecider loanDecider;







}
