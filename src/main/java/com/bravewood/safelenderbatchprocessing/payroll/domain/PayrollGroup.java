package com.bravewood.safelenderbatchprocessing.payroll.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;




@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payroll_group",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"productId","employerId","upload_period"},
                name = "payroll_group_unique_identifiers")})
//@Table(name = "payroll_customer")
public class PayrollGroup {



    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private Long employerId;
    @DateTimeFormat(pattern="yyyy-MM")
    private String upload_period;
    private LocalDate uploaded_date = LocalDate.now();
    private Integer status;
    private String name;
    private String user_email;











}
