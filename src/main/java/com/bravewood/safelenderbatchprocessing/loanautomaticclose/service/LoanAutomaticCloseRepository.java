package com.bravewood.safelenderbatchprocessing.loanautomaticclose.service;

import com.bravewood.safelenderbatchprocessing.loanautomaticclose.data.request.LoanAutomaticClose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanAutomaticCloseRepository extends JpaRepository<LoanAutomaticClose,Integer> {
}
