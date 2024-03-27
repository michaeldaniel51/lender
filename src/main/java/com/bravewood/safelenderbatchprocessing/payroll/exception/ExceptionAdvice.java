package com.bravewood.safelenderbatchprocessing.payroll.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;


@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler(PayrollException.class)
    public ResponseEntity<ExceptionResponse> handlePayrollException(PayrollException ex) {
        log.error("Payroll", ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setErrorMessage(ex.getMessage());

        exceptionResponse.setTime(LocalDateTime.now());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("Date", ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setMessage("enter only year and month");
        exceptionResponse.setTime(LocalDateTime.now());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> handleRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("RequestParameters", ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setErrorMessage(ex.getMessage());
        exceptionResponse.setTime(LocalDateTime.now());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
