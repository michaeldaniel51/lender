package com.bravewood.safelenderbatchprocessing.payroll.service;

import com.bravewood.safelenderbatchprocessing.payroll.domain.BankScheduleAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanDecider;
import com.bravewood.safelenderbatchprocessing.payroll.exception.PayrollException;
import com.bravewood.safelenderbatchprocessing.payroll.repository.BankScheduleAnalysisRepo;
import com.bravewood.safelenderbatchprocessing.payroll.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BankScheduleAnalysisService {

    @Autowired
    private BankScheduleAnalysisRepo bankScheduleAnalysisRepo;

    @Autowired
    private LoanDeciderService loanDeciderService;

    public BankScheduleAnalysis add (BankScheduleAnalysis bankScheduleAnalysis){

        loanDeciderService.add(bankScheduleAnalysis.getLoanDecider());
        bankScheduleAnalysis.setLoanDecider(bankScheduleAnalysis.getLoanDecider());

        return bankScheduleAnalysisRepo.save(bankScheduleAnalysis);
    }


    public BankScheduleAnalysis update (Long id,BankScheduleAnalysis bankScheduleAnalysis){

        BankScheduleAnalysis bankSchedule = bankScheduleAnalysisRepo.findById(id).orElseThrow(()-> new PayrollException("not found"));


        bankSchedule.setId(id);
            if (Objects.nonNull(bankScheduleAnalysis.getMinGrossPay())) {
                bankSchedule.setMinGrossPay(bankScheduleAnalysis.getMinGrossPay());
            }

        if (Objects.nonNull(bankScheduleAnalysis.getMinNetPay())) {
            bankSchedule.setMinNetPay(bankScheduleAnalysis.getMinNetPay());
        }

        if (Objects.nonNull(bankScheduleAnalysis.getDSR())) {
            bankSchedule.setDSR(bankScheduleAnalysis.getDSR());
        }

        if (Objects.nonNull(bankScheduleAnalysis.getAllowNull())) {
            bankSchedule.setAllowNull(bankScheduleAnalysis.getAllowNull());
        }

        if (Objects.nonNull(bankScheduleAnalysis.getMonthInView())) {
            bankSchedule.setMonthInView(bankScheduleAnalysis.getMonthInView());
        }

        if (Objects.nonNull(bankScheduleAnalysis.getMinNumberOfSalaryPayment())) {
            bankSchedule.setMinNumberOfSalaryPayment(bankScheduleAnalysis.getMinNumberOfSalaryPayment());
        }
        if (Objects.nonNull(bankScheduleAnalysis.getPreferredPaymentMode())) {
            bankSchedule.setPreferredPaymentMode(bankScheduleAnalysis.getPreferredPaymentMode());
        }
        if (Objects.nonNull(bankScheduleAnalysis.getLoanDecider())) {
            LoanDecider loanDecider = bankSchedule.getLoanDecider();
            loanDecider.setId(bankSchedule.getLoanDecider().getId());
            loanDeciderService.update(loanDecider.getId(), loanDecider);
            bankSchedule.setLoanDecider(loanDecider);

        }else {
            throw new PayrollException("update was not successful");
        }

            return bankScheduleAnalysisRepo.save(bankSchedule);
        }



    public List<BankScheduleAnalysis> getAll (){

        return bankScheduleAnalysisRepo.findAll();
    }

    public BankScheduleAnalysis getOne (Long id){

        return bankScheduleAnalysisRepo.findById(id).orElseThrow(()-> new PayrollException("not found"));
    }

    public ResponseMessage delete (Long id){

        ResponseMessage responseMessage = new ResponseMessage();

        if (bankScheduleAnalysisRepo.existsById(id)){
            bankScheduleAnalysisRepo.deleteById(id);
            responseMessage.setMessage("Bank Schedule was deleted Successfully");

        }else {
            responseMessage.setMessage("delete was not successful");
        }
        return responseMessage;
    }



}
