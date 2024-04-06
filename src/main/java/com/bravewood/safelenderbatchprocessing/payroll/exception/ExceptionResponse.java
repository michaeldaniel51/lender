package com.bravewood.safelenderbatchprocessing.payroll.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private String errorMessage;
    private String message;
    private LocalDateTime time;




}
