package com.bravewood.safelenderbatchprocessing.payroll.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "loan_offer")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanOffer {


    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal minimumAmount;
    private BigDecimal maximumAmount;
    private BigDecimal interest;
    private Long loanTerm;


}
