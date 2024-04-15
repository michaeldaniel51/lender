package com.bravewood.safelenderbatchprocessing.payroll.repository;


import com.bravewood.safelenderbatchprocessing.payroll.domain.PayrollGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PayrollGroupRepo extends JpaRepository<PayrollGroup,Long> {





}
