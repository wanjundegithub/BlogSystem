package com.company.blog.util;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static  final Long serializableUID=1L;

    private String message;

    private int resultCode;

    private T data;

    public Result(String message, int resultCode) {
        this.message = message;
        this.resultCode = resultCode;
    }

    public Result() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
