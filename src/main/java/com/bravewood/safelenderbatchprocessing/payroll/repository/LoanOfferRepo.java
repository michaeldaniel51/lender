package com.bravewood.safelenderbatchprocessing.payroll.repository;

import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanOfferRepo  extends JpaRepository<LoanOffer,Long> {
}
