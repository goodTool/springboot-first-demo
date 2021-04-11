package com.example.springbootfirstdemo.myexception;

import com.example.springbootfirstdemo.bean.PageVO;

public class ResponseResultUtil {

    /**
     * @param code      响应码
     * @param message   相应信息
     * @param any       返回的数据
     * @description     请求成功返回对象
     */
    public final ResponseResultVO success(int code, String message, PageVO page, Object any) {
        return new ResponseResultVO(code, message, page, any);
    }

    /**
     * @param any   返回的数据
     * @description 请求成功返回对象
     */
    public final ResponseResultVO success(Object any) {
        int code = ResponseCodeEnum.SUCCESS.getCode();
        String message = ResponseCodeEnum.SUCCESS.getMessage();
        return this.success(code, message, null, any);
    }

    /**
     * @param any   返回的数据
     * @description 请求成功返回对象
     */
    public final ResponseResultVO success(Object any, PageVO page) {
        int code = ResponseCodeEnum.SUCCESS.getCode();
        String message = ResponseCodeEnum.SUCCESS.getMessage();
        return this.success(code, message, page, any);
    }

    /**
     * @description 请求成功返回对象
     */
    public final ResponseResultVO success() {
        return this.success(null);
    }

    /**
     * @param responseCode  返回的响应码所对应的枚举类
     * @description         请求失败返回对象
     */
    public final ResponseResultVO error(ResponseCodeEnum responseCode) {
        return new ResponseResultVO(responseCode.getCode(), responseCode.getMessage(), null, null);
    }

    /**
     * @param code      响应码
     * @param message   相应信息
     * @description     请求失败返回对象
     */
    public final ResponseResultVO error(int code, String message) {
        return new ResponseResultVO(code, message, null, null);
    }
}
