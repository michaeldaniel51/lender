package com.bravewood.safelenderbatchprocessing.payroll.service;

import com.bravewood.safelenderbatchprocessing.payroll.domain.BankScheduleAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.domain.CreditReportAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanDecider;
import com.bravewood.safelenderbatchprocessing.payroll.exception.PayrollException;
import com.bravewood.safelenderbatchprocessing.payroll.repository.BankScheduleAnalysisRepo;
import com.bravewood.safelenderbatchprocessing.payroll.repository.CreditReportAnalysisRepo;
import com.bravewood.safelenderbatchprocessing.payroll.repository.LoanDeciderRepo;
import com.bravewood.safelenderbatchprocessing.payroll.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CreditReportAnalysisService {

    @Autowired
    private CreditReportAnalysisRepo creditReportAnalysisRepo;

    @Autowired
    private LoanDeciderService loanDeciderService;



    public CreditReportAnalysis add (CreditReportAnalysis creditReportAnalysis){

        loanDeciderService.add(creditReportAnalysis.getLoanDecider());
        creditReportAnalysis.setLoanDecider(creditReportAnalysis.getLoanDecider());

        return creditReportAnalysisRepo.save(creditReportAnalysis);
    }


    public CreditReportAnalysis update (Long id,CreditReportAnalysis creditReportAnalysis){

        CreditReportAnalysis creditReport = creditReportAnalysisRepo.findById(id).orElseThrow(()-> new PayrollException("not found"));

        creditReport.setId(id);

        if (Objects.nonNull(creditReportAnalysis.getCategoryName())
                && !"".equalsIgnoreCase(creditReportAnalysis.getCategoryName())) {
            creditReport.setCategoryName(creditReportAnalysis.getCategoryName());
        }

        if (Objects.nonNull(creditReportAnalysis.getInterestRate())) {
            creditReport.setInterestRate(creditReportAnalysis.getInterestRate());
        }

        if (Objects.nonNull(creditReportAnalysis.getMaximumLoanAmount())) {
            creditReport.setMaximumLoanAmount(creditReportAnalysis.getMaximumLoanAmount());
        }
        if (Objects.nonNull(creditReportAnalysis.getMaximumMonthInArrears())) {
            creditReport.setMaximumMonthInArrears(creditReportAnalysis.getMaximumMonthInArrears());
        }
        if (Objects.nonNull(creditReportAnalysis.getMonthlyDSR())) {
            creditReport.setMonthlyDSR(creditReportAnalysis.getMonthlyDSR());
        }
        if (Objects.nonNull(creditReportAnalysis.getLoanDecider())) {
            LoanDecider loanDecider = creditReportAnalysis.getLoanDecider();
            loanDecider.setId(creditReportAnalysis.getLoanDecider().getId());
            loanDeciderService.update(loanDecider.getId(), loanDecider);
            creditReportAnalysis.setLoanDecider(loanDecider);

        }else {
            throw new PayrollException("update was not successful");
        }
        return creditReportAnalysisRepo.save(creditReport);
    }

    public List<CreditReportAnalysis> getAll (){

        return creditReportAnalysisRepo.findAll();
    }

    public CreditReportAnalysis getOne (Long id){

        return creditReportAnalysisRepo.findById(id).orElseThrow(()-> new PayrollException("not found"));
    }

    public ResponseMessage delete (Long id){

        ResponseMessage responseMessage = new ResponseMessage();

        if (creditReportAnalysisRepo.existsById(id)){
            creditReportAnalysisRepo.deleteById(id);
            responseMessage.setMessage("Credit Report Analysis was deleted Successfully");

        }else {
            responseMessage.setMessage("delete was not successful");
        }
        return responseMessage;
    }



//        credit report analysis
//        "maximumMonthInArrears": 22,
//        "maximumLoanAmount": 23,
//        "monthlyDSR": 2,
//        "categoryName": "namesTrip",
//        "interestRate": 2

    //      bank schedule analysis
//          "monthInView": 2,
//          "minNetPay": 200,
//          "minGrossPay": 300,
//          "preferredPaymentMode": "names",
//          "minNumberOfSalaryPayment": 800,
//          "allowNull": "names",
//          "dsr": 2









}
