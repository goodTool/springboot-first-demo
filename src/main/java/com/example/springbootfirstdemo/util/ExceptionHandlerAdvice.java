package com.example.springbootfirstdemo.util;

import com.example.springbootfirstdemo.util.exception.RestException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ExceptionHandlerAdvice {
    /**
     * 处理Rest接口请求时的异常
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(RestException.class)
    @ResponseBody
    public Map<String, Object> restError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        RestException restException = (RestException) ex;
        Map<String, Object> map = new HashMap<>();
        map.put("exception", null != restException.getT() ? restException.getT() : restException);
        map.put("errorMessage", restException.getMessage());
        map.put("url", request.getRequestURL());
        map.put("statusCode",  restException.getCode());
        return map;
    }

}
