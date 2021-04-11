package com.example.springbootfirstdemo.util.exception;

public class GeneralException extends Exception{

    private String errorCode;
    private String priviceCode;


    public GeneralException(String errorCode,String message){
        super(message);
        this.errorCode = errorCode;

    }
    public GeneralException(String errorCode,Throwable rowable){
        super(errorCode,rowable);
        this.errorCode = errorCode;


    }



    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPriviceCode() {
        return priviceCode;
    }

    public void setPriviceCode(String priviceCode) {
        this.priviceCode = priviceCode;
    }
}
