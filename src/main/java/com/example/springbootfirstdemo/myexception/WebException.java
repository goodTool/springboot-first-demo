package com.example.springbootfirstdemo.myexception;

public class WebException extends BaseBusinessException {

    public WebException(ResponseCodeEnum responseCodeEnum) {

        super(responseCodeEnum);
    }
}
