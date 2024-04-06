package com.bravewood.safelenderbatchprocessing.payroll.repository;

import com.bravewood.safelenderbatchprocessing.payroll.domain.CreditReportAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditReportAnalysisRepo extends JpaRepository<CreditReportAnalysis,Long> {
}
