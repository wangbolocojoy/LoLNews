package com.shop.newshop_application.callback;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Unir|Superman
 * on 2017/12/4 13:10.
 * on Administrator
 * on NewShop_Application
 */

public abstract class JsonCallBack<T>  extends AbsCallback<T>{

    private Class<T> clazz;

    public JsonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallBack() {
    }

    /**
     * 拿到响应后，将数据转换成需要的格式，子线程中执行，可以是耗时操作
     *
     * @param response 需要转换的对象
     * @return 转换后的结果
     * @throws Exception 转换过程发生的异常
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body=response.body();
        if (body==null){
            return null;
        }
        Gson gson=new Gson();
        T Data=null;
        JsonReader jsonReader=new JsonReader(body.charStream());
        if (clazz!=null) {
            Data= gson.fromJson(String.valueOf(jsonReader),clazz);
        }
        return Data;
    }
}
