package com.example.springbootfirstdemo.myexception;

public enum ResponseCodeEnum {

    SUCCESS(200, "操作成功"),

    ERROR1(233, "没有登录"),

    ERROR_SYS(500,"未知错误"),

    ERROR2(-1, "用户或密码错误"),

    ERROR3(-2, "用户不存在");



    private Integer code;
    private String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public final Integer getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

}
