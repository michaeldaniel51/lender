package com.bravewood.safelenderbatchprocessing.payroll.api;

import com.bravewood.safelenderbatchprocessing.payroll.domain.BankScheduleAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.domain.CreditReportAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.response.ResponseMessage;
import com.bravewood.safelenderbatchprocessing.payroll.service.BankScheduleAnalysisService;
import com.bravewood.safelenderbatchprocessing.payroll.service.CreditReportAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bravewood.safelenderbatchprocessing.config.ApiConstants.CREDIT_REPORT_ANALYSIS_BASE_URL;

@RestController
@RequestMapping(CREDIT_REPORT_ANALYSIS_BASE_URL)
public class CreditReportAnalysisApi {


    @Autowired
    private CreditReportAnalysisService creditReportAnalysisService;

    @PostMapping("/save")
    public CreditReportAnalysis add (@RequestBody CreditReportAnalysis creditReportAnalysis) {

        return creditReportAnalysisService.add(creditReportAnalysis);

    }

    @PutMapping("/update/{id}")
    public CreditReportAnalysis update (@RequestBody CreditReportAnalysis creditReportAnalysis,@PathVariable Long id) {

        return creditReportAnalysisService.update(id,creditReportAnalysis);

    }

    @GetMapping("/{id}")
    public CreditReportAnalysis getOne (@PathVariable Long id) {

        return creditReportAnalysisService.getOne(id);

    }

    @GetMapping("/list")
    public List<CreditReportAnalysis> getAll () {

        return creditReportAnalysisService.getAll();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage delete (@PathVariable Long id) {

        return creditReportAnalysisService.delete(id);

    }



}
