package com.bravewood.safelenderbatchprocessing.payroll.repository;

import com.bravewood.safelenderbatchprocessing.payroll.domain.BankScheduleAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankScheduleAnalysisRepo extends JpaRepository<BankScheduleAnalysis,Long> {
}
