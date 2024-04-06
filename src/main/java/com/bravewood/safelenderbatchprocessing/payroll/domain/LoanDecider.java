package com.bravewood.safelenderbatchprocessing.payroll.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "loan_decider")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDecider {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long productId;
    private String user_email;
//    @ManyToOne
//    private BankScheduleAnalysis bankScheduleAnalysis;
//    @ManyToOne
//    private CreditReportAnalysis creditReportAnalysis;
//


}
