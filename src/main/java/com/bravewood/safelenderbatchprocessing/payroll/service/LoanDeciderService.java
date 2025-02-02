package com.bravewood.safelenderbatchprocessing.payroll.service;

import com.bravewood.safelenderbatchprocessing.payroll.domain.BankScheduleAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.domain.CreditReportAnalysis;
import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanDecider;
import com.bravewood.safelenderbatchprocessing.payroll.exception.PayrollException;
import com.bravewood.safelenderbatchprocessing.payroll.repository.LoanDeciderRepo;
import com.bravewood.safelenderbatchprocessing.payroll.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LoanDeciderService {



    @Autowired
    private LoanDeciderRepo loanDeciderRepo;



    public LoanDecider add (LoanDecider loanDecider){


        if (!loanDecider.getUser_email().isEmpty()){
            loanDecider.setUser_email(loanDecider.getUser_email());
        }else {
            throw new PayrollException("email is wrong and cannot be empty");
        }

        return loanDeciderRepo.save(loanDecider);
    }


    public LoanDecider update (Long id,LoanDecider loanDecider){

        LoanDecider loanDecide = loanDeciderRepo.findById(id).orElseThrow(()-> new PayrollException("not found"));

        if (id.equals(loanDecide.getId())) {

            loanDecide.setId(loanDecide.getId());

            if (Objects.nonNull(loanDecider.getUser_email())
                    && !"".equalsIgnoreCase(loanDecider.getUser_email())) {
                loanDecide.setUser_email(loanDecider.getUser_email());
            }

            if (Objects.nonNull(loanDecider.getName())
                    && !"".equalsIgnoreCase(loanDecider.getName())) {
                loanDecide.setName(loanDecider.getName());
            }

            if (Objects.nonNull(loanDecider.getProductId())) {
                loanDecide.setProductId(loanDecider.getProductId());
            }

        }else {
            throw new PayrollException("update was not successful");
        }
        return loanDeciderRepo.save(loanDecide);
    }

    public List<LoanDecider> getAll (){

        return loanDeciderRepo.findAll();
    }

    public LoanDecider getOne (Long id){

        return loanDeciderRepo.findById(id).orElseThrow(()-> new PayrollException("not found"));
    }

    public ResponseMessage delete (Long id){

        ResponseMessage responseMessage = new ResponseMessage();

        if (loanDeciderRepo.existsById(id)){
            loanDeciderRepo.deleteById(id);
            responseMessage.setMessage("Loan Decider was deleted Successfully");

        }else {
            responseMessage.setMessage("delete was not successful");
        }
        return responseMessage;
    }








}
