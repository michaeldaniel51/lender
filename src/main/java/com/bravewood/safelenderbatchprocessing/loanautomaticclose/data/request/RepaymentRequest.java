package com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class RepaymentRequest {


    private Integer paymentTypeId;
    private BigDecimal transactionAmount;
    private String transactionDate;
    private String note;
    private String accountNumber;
    private String checkNumber;
    private String routingCode;
    private String receiptNumber;
    private String bankNumber;
    private String locale;
    private String dateFormat;


}
