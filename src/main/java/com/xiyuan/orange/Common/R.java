package com.xiyuan.orange.Common;


/**
 * 封装通用响应体
 * @param <T>
 */
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> success(T object){
        R requestBody = new R();
        requestBody.code = 200;
        requestBody.data = object;
        return requestBody;
    }

    public static <T> R<T> success(){
        R requestBody = new R();
        requestBody.code = 200;
        return requestBody;
    }

    public static <T> R<T> error(String msg){
        R requestBody = new R();
        requestBody.code = 500;
        requestBody.msg = msg;
        return requestBody;
    }

    public Integer getCode() {
        return code;
    }

    public R setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public R setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }
}
