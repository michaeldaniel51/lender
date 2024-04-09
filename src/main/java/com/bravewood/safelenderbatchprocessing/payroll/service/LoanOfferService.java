package com.bravewood.safelenderbatchprocessing.payroll.service;


import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanDecider;
import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanOffer;
import com.bravewood.safelenderbatchprocessing.payroll.exception.PayrollException;
import com.bravewood.safelenderbatchprocessing.payroll.repository.LoanOfferRepo;
import com.bravewood.safelenderbatchprocessing.payroll.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LoanOfferService {

    @Autowired
    private LoanOfferRepo loanOfferRepo;


    public LoanOffer add (LoanOffer loanOffer){
        return loanOfferRepo.save(loanOffer);
    }


    public LoanOffer update (Long id,LoanOffer loanOffer){

        LoanOffer offer = loanOfferRepo.findById(id).orElseThrow(()-> new PayrollException("not found"));

        if (id.equals(offer.getId())) {

            loanOffer.setId(offer.getId());

            if (Objects.nonNull(loanOffer.getLoanTerm())){
                offer.setLoanTerm(loanOffer.getLoanTerm());
            }
            if (Objects.nonNull(loanOffer.getInterest())){
                offer.setInterest(loanOffer.getInterest());
            }
            if (Objects.nonNull(loanOffer.getMaximumAmount())){
                offer.setMaximumAmount(loanOffer.getMaximumAmount());
            }
            if (Objects.nonNull(loanOffer.getMinimumAmount())){
                offer.setMinimumAmount(loanOffer.getMinimumAmount());
            }

        }else {
            throw new PayrollException("update was not successful");
        }
        return loanOfferRepo.save(offer);
    }

    public List<LoanOffer> getAll(){

        return loanOfferRepo.findAll();
    }

    public LoanOffer getOne (Long id){

        return loanOfferRepo.findById(id).orElseThrow(()-> new PayrollException("not found"));
    }

    public ResponseMessage delete (Long id){

        ResponseMessage responseMessage = new ResponseMessage();

        if (loanOfferRepo.existsById(id)){
            loanOfferRepo.deleteById(id);
            responseMessage.setMessage("Loan Offer was deleted Successfully");

        }else {
            responseMessage.setMessage("delete was not successful");
        }
        return responseMessage;
    }










}
