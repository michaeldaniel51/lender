package com.bravewood.safelenderbatchprocessing.payroll.api;

import com.bravewood.safelenderbatchprocessing.payroll.domain.CreditReportAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanDecider;
import com.bravewood.safelenderbatchprocessing.payroll.response.ResponseMessage;
import com.bravewood.safelenderbatchprocessing.payroll.service.CreditReportAnalysisService;
import com.bravewood.safelenderbatchprocessing.payroll.service.LoanDeciderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bravewood.safelenderbatchprocessing.config.ApiConstants.LOAN_DECIDER_BASE_URL;

@RestController
@RequestMapping(LOAN_DECIDER_BASE_URL)
public class LoanDeciderApi {

    @Autowired
    private LoanDeciderService loanDeciderService;

    @PostMapping("/save")
    public LoanDecider add (@RequestBody LoanDecider loanDecider) {

        return loanDeciderService.add(loanDecider);

    }

    @PutMapping("/update/{id}")
    public LoanDecider update (@RequestBody LoanDecider loanDecider,@PathVariable Long id) {

        return loanDeciderService.update(id,loanDecider);

    }

    @GetMapping("/{id}")
    public LoanDecider getOne (@PathVariable Long id) {

        return loanDeciderService.getOne(id);

    }

    @GetMapping("/list")
    public List<LoanDecider> getAll () {

        return loanDeciderService.getAll();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage delete (@PathVariable Long id) {

        return loanDeciderService.delete(id);

    }







}
