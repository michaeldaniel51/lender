package com.bravewood.safelenderbatchprocessing.payroll.api;


import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanDecider;
import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanOffer;
import com.bravewood.safelenderbatchprocessing.payroll.response.ResponseMessage;
import com.bravewood.safelenderbatchprocessing.payroll.service.LoanDeciderService;
import com.bravewood.safelenderbatchprocessing.payroll.service.LoanOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bravewood.safelenderbatchprocessing.config.ApiConstants.LOAN_DECIDER_BASE_URL;
import static com.bravewood.safelenderbatchprocessing.config.ApiConstants.LOAN_OFFER_BASE_URL;

@RestController
@RequestMapping(LOAN_OFFER_BASE_URL)
public class LoanOfferApi {

    @Autowired
    private LoanOfferService loanOfferService;

    @PostMapping("/save")
    public LoanOffer add (@RequestBody LoanOffer loanOffer) {

        return loanOfferService.add(loanOffer);

    }

    @PutMapping("/update/{id}")
    public LoanOffer update (@RequestBody LoanOffer loanOffer,@PathVariable Long id) {

        return loanOfferService.update(id,loanOffer);

    }

    @GetMapping("/{id}")
    public LoanOffer getOne (@PathVariable Long id) {

        return loanOfferService.getOne(id);

    }

    @GetMapping("/list")
    public List<LoanOffer> getAll () {

        return loanOfferService.getAll();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage delete (@PathVariable Long id) {

        return loanOfferService.delete(id);

    }







}
