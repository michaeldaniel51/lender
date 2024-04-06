package com.bravewood.safelenderbatchprocessing.payroll.repository;


import com.bravewood.safelenderbatchprocessing.payroll.domain.PayrollGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PayrollGroupRepo extends JpaRepository<PayrollGroup,Long> {

   // List<PayrollGroup> findByUploaded_dateAndStatus(LocalDate uploaded_date,Integer status);




}
