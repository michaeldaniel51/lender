package com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class RepaymentResponse {



    private Long officeId;
    private Long clientId;
    private Long loanId;
    private Long resourceId;
    private Object changes;
    private Integer status;
    private String message;


}
