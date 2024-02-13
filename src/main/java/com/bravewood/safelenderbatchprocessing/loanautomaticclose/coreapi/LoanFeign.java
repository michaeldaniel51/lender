package com.bravewood.safelenderbatchprocessing.loanautomaticclose.coreapi;


import com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.request.RepaymentRequest;
import com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.response.RepaymentResponse;
import com.bravewood.safelenderbatchprocessing.openfeignconfig.LoanFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "loanAutomaticClose", url = "${fineract.api.base.url}/loans",configuration = LoanFeignConfiguration.class)
public interface LoanFeign {


    @PostMapping(value = "/{loanId}/transactions",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    RepaymentResponse repayResponse (@PathVariable Integer loanId, @RequestParam("command") String command, @RequestBody RepaymentRequest repaymentRequest, @RequestHeader("Authorization") String authorization, @RequestHeader("Fineract-Platform-Tenantid") String FineractPlatformTenantid);


}
