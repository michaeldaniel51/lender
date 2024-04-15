package com.bravewood.safelenderbatchprocessing.payroll.repository;

import com.bravewood.safelenderbatchprocessing.payroll.domain.BankScheduleAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankScheduleAnalysisRepo extends JpaRepository<BankScheduleAnalysis,Long> {
}
