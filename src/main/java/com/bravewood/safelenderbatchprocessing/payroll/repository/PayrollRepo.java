package com.bravewood.safelenderbatchprocessing.payroll.repository;

import com.bravewood.safelenderbatchprocessing.payroll.domain.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepo extends JpaRepository<Payroll,Long> {



}
