package com.example.springbootfirstdemo.myexception;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BaseBusinessException extends RuntimeException {

    private final Logger logger = LogManager.getLogger(BaseBusinessException.class);

    private Integer code;

    // 给子类用的方法
    public BaseBusinessException(ResponseCodeEnum responseCodeEnum) {
        this(responseCodeEnum.getMessage(), responseCodeEnum.getCode());
    }

    private BaseBusinessException(String message, Integer code) {
        super(message);
        logger.info("异常位置。。。。。。。");
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
