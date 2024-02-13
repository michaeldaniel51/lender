package com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "laon_authomatic_close")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class LoanAutomaticClose {


        @Id
        private Integer loan_id;
        private BigDecimal total_outstanding_derived;
        private Integer loan_status_id;









}
