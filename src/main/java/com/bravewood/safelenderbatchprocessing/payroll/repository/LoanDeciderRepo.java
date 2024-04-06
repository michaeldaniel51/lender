package com.bravewood.safelenderbatchprocessing.payroll.repository;

import com.bravewood.safelenderbatchprocessing.payroll.domain.LoanDecider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDeciderRepo extends JpaRepository<LoanDecider,Long> {
}
