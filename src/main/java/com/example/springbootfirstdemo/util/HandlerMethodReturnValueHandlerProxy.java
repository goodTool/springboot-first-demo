package com.example.springbootfirstdemo.util;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {

    private static final String STATUS_CODE_SUCCEEDED = "200";

    private HandlerMethodReturnValueHandler proxyObject;


    public HandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return proxyObject.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("statusCode", STATUS_CODE_SUCCEEDED);
        resultMap.put("errorMessage", "");
        resultMap.put("data", returnValue);

        proxyObject.handleReturnValue(resultMap, returnType, mavContainer, webRequest);
    }
}
