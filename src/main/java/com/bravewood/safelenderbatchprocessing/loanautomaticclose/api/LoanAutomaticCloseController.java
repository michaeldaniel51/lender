package com.bravewood.safelenderbatchprocessing.loanautomaticclose.api;

import com.bravewood.safelenderbatchprocessing.loanautomaticclose.service.LoanAutomaticCloseService;
import com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.request.LoanAutomaticClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/automatic")
public class LoanAutomaticCloseController {



    @Autowired
    private LoanAutomaticCloseService loanAutomaticCloseService;


    @GetMapping("/list")
    public List<LoanAutomaticClose> getAll(){
        return loanAutomaticCloseService.getAll();
    }


    @PostMapping("/close")
    public void closeLoan(){
         loanAutomaticCloseService.closeLoan();
    }


}
