package com.example.springbootfirstdemo.myexception;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理: 使用 @RestControllerAdvice + @ExceptionHandler 注解方式实现全
 *  局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    //申明捕获那个异常类，指当controller中抛出这个指定的异常类的时候，都会转到这个方法中来处理异常
    @ExceptionHandler({Exception.class})
    public ResponseResultVO globalExceptionHandler(Exception e) {
        this.logger.error(e.getMessage(), e);
        this.logger.info("Exception........");
        return new ResponseResultUtil().error(ResponseCodeEnum.ERROR_SYS);
    }


    @ExceptionHandler({BaseBusinessException.class})
    public ResponseResultVO BusinessExceptionHandler(BaseBusinessException e) {
        this.logger.info("BaseBusinessException注释的异常。。。");
        this.logger.error(e);
        return new ResponseResultUtil().error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({ServiceException.class})
    public ServiceException ServerExceptionHandler(ServiceException e) {
        logger.info("GeneralException异常。。。");
        this.logger.error(e);
        return new ServiceException(e.getRetCd(),e.getMsgDes());
    }

}
