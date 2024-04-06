package com.bravewood.safelenderbatchprocessing.payroll.api;


import com.bravewood.safelenderbatchprocessing.payroll.domain.BankScheduleAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.response.ResponseMessage;
import com.bravewood.safelenderbatchprocessing.payroll.service.BankScheduleAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bravewood.safelenderbatchprocessing.config.ApiConstants.BANK_SCHEDULE_ANALYSIS_BASE_URL;

@RestController
@RequestMapping(BANK_SCHEDULE_ANALYSIS_BASE_URL)
public class BankScheduleAnalysisApi {

    @Autowired
    private BankScheduleAnalysisService bankScheduleAnalysisService;

    @PostMapping("/save")
    public BankScheduleAnalysis add (@RequestBody BankScheduleAnalysis bankScheduleAnalysis) {

       return bankScheduleAnalysisService.add(bankScheduleAnalysis);

    }

    @PutMapping("/update/{id}")
    public BankScheduleAnalysis update (@RequestBody BankScheduleAnalysis bankScheduleAnalysis,@PathVariable Long id) {

        return bankScheduleAnalysisService.update(id,bankScheduleAnalysis);

    }

    @GetMapping("/{id}")
    public BankScheduleAnalysis getOne (@PathVariable Long id) {

        return bankScheduleAnalysisService.getOne(id);

    }

    @GetMapping("/list")
    public List<BankScheduleAnalysis> getAll () {

        return bankScheduleAnalysisService.getAll();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage delete (@PathVariable Long id) {

        return bankScheduleAnalysisService.delete(id);

    }




    }
