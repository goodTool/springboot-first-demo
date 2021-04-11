package com.example.springbootfirstdemo.myexception;

import com.example.springbootfirstdemo.bean.PageVO;

public class ResponseResultVO {

    /**
     * @description 响应码
     */
    private int code;

    /**
     * @description 响应消息
     */
    private String message;

    /**
     * @description 分页对象 （如果用不到，这个可以不写）
     */
    private PageVO page;

    /**
     * @description 数据
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PageVO getPage() {
        return page;
    }

    public void setPage(PageVO page) {
        this.page = page;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseResultVO(int code, String message, PageVO page, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.page = page;
        this.data = data;
    }
}
