package com.example.springbootfirstdemo.myexception;

public class GeneralException extends RuntimeException {

    /**
     * 自定义异常类用来替代Exception规避sonar扫描
     */
    private static final long serialVersionUID = -2985157854697142072L;
    private String retCd; // 异常对应的返回码
    private String msgDes; // 异常对应的描述信息

    public GeneralException() {
        super();
    }

    public GeneralException(String message) {
        super(message);
        msgDes = message;
    }

    public GeneralException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }


    public String getRetCd() {
        return retCd;
    }

    public void setRetCd(String retCd) {
        this.retCd = retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }

    public void setMsgDes(String msgDes) {
        this.msgDes = msgDes;
    }
}
