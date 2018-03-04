package com.blog.news.http.result;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by a on 2018/1/4.
 */

public class BaseResult implements Serializable {

    /** 参数错误 **/
    public static final int RS_PARAM_ERROR = 400;
    /** 请求成功 **/
    public static final int RS_OK = 200;
    /** 请求失败 **/
    public static final int RS_FAILED = 1;
    /** 对象不存在 **/
    public static final int RS_NOT_EXISTS = 2;

    /** 错误代码 **/
    @JSONField(name = "status")
    private int status;
    /** 返回消息 **/
    @JSONField(name = "msg")
    private String msg;
    /** 服务器时间搓（毫秒） **/
//    @JSONField(name = "sysTime")
//    private long sysTime;
    /** 返回数据 **/
    @JSONField(name = "data")
    private Object data;

    @Override
    public String toString() {
        return "BaseResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public static int getRsParamError() {
        return RS_PARAM_ERROR;
    }

    public static int getRsOk() {
        return RS_OK;
    }

    public static int getRsFailed() {
        return RS_FAILED;
    }

    public static int getRsNotExists() {
        return RS_NOT_EXISTS;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
