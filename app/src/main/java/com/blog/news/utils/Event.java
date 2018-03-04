package com.blog.news.utils;

/**
 * Created by SuperDaHuiLang on 2018/3/1.
 */

public class Event<T> {
    private int code;
    private T data;
    public Event(int code) {
        this.code = code;
    }
    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
