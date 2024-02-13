package com.bravewood.safelenderbatchprocessing.loanautomaticclose.service;

import com.bravewood.safelenderbatchprocessing.loanautomaticclose.coreapi.LoanFeign;
import com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.request.RepaymentRequest;
import com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.response.RepaymentResponse;
import com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.request.LoanAutomaticClose;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoanAutomaticCloseService {


    @Autowired
    private LoanAutomaticCloseRepository loanAutomaticCloseRepository;

    @Autowired
    private LoanFeign loanFeign;


    @Value("${fineract.api.authorization.value}")
    private String authorizationValue;

    @Value("${fineract.api.tenant.value}")
    private String tenantId;


    public List<LoanAutomaticClose> getAll(){
        return loanAutomaticCloseRepository.findAll();
    }


    @Scheduled(cron = "${app.scheduler.repayment.process.cron}")
    public void closeLoan() {

        try {

            List<RepaymentResponse> responses = new ArrayList<>();
            RepaymentResponse repaymentResponse = null;

            String dateFormat = "dd MMMM yyyy";
            String locale = "en";
            String transactionDate = DateTimeFormatter.ofPattern("dd MMM yyyy").format(LocalDateTime.now());

            RepaymentRequest request = new RepaymentRequest();
            request.setDateFormat(dateFormat);
            request.setLocale(locale);
            request.setTransactionDate(transactionDate);
            request.setNote("AutoRepayment");
            request.setPaymentTypeId(1);

            String command = "repayment";

           String authorization = new StringBuilder().append("Basic ").append(authorizationValue).toString();

            for (LoanAutomaticClose loan : getAll()) {
                    log.info(loan.toString());
                log.info(loan.getLoan_id().toString());
                request.setTransactionAmount(loan.getTotal_outstanding_derived());

                log.info(request.toString());
                    repaymentResponse = loanFeign.repayResponse(loan.getLoan_id(), command, request, authorization, tenantId);

                  responses.add(repaymentResponse);

            }

            if (!ObjectUtils.isEmpty(responses)) {
                log.info("Request Was Successful: All Loans Closed");
            }else {
               log.info("Request Was Successful: Found No Loans To Close");
            }
            log.info(responses.toString());

        } catch (FeignException e) {
            log.error("ResponseBody: " + e.contentUTF8());
            throw new RuntimeException("an error occurred");

        }
    }





}
