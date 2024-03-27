package com.bravewood.safelenderbatchprocessing.payroll.exception;



public class PayrollException extends RuntimeException {

    private String message;

    public PayrollException(String errorMessage,Throwable err) {
        super(errorMessage,err);
    }

    public PayrollException(String message) {
        super(message);
    }

    public PayrollException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public PayrollException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public PayrollException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public PayrollException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }



}
