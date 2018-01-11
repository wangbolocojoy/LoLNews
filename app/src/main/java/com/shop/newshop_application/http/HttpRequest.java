package com.shop.newshop_application.http;

import android.content.Context;
import android.text.TextUtils;

import com.rhino.ui.utils.Log;
import com.shop.newshop_application.http.request.HttpParams;


import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http2.Header;

/**
 * Created by Unir|Superman
 * on 2017/11/22 10:16.
 * on Administrator
 * on NewShop_Application
 */


public class HttpRequest {

    /**
     * 设置默认超时时间
     */
    private static final int DF_TIME =15 ;
    private String token;
    private OkHttpClient mOkHttpClient;
    public HttpRequest(Context context){
        mOkHttpClient=new OkHttpClient().newBuilder()
                .connectTimeout(DF_TIME, TimeUnit.SECONDS)
                .readTimeout(DF_TIME,TimeUnit.SECONDS)
                .writeTimeout(DF_TIME,TimeUnit.SECONDS)
                .cookieJar(new CookiejarManger())
                .build();
    }


    /**
     * 设置token
     *
     * @param token
     *            从服务器获取
     */
    public void setToken(String token) {
        this.token = token;
    }


    /**
     * POST请求
     *
     * @param url
     *            请求url
     * @param callback
     *            回调
     */
    public void doPost(String url, Callback callback) {
        doPost(url, null, callback);
    }

    /**
     * POST请求
     *
     * @param url
     *            请求url
     * @param params
     *            请求参数
     * @param callback
     *            回调
     */
    public void doPost(String url, HttpParams params, Callback callback) {

		String httpUrl = buildHttpUrl(url, params);
		Log.i("httpUrl = " + httpUrl);
		Request.Builder requestBuilder = new Request.Builder().url(httpUrl);
		RequestBody mRequestBody = new FormBody.Builder().build();
		Request mRequest = requestBuilder.post(mRequestBody).build();
		mOkHttpClient.newCall(mRequest).enqueue(callback);
    }

    /**
     * Get请求
     *
     * @param url
     *            请求url
     * @param callback
     *            回调
     */
    public void doGet(String url, Callback callback) {
        doGet(url, null, callback);
    }

    /**
     * Get请求
     *
     * @param url
     *            请求url
     * @param params
     *            请求参数
     * @param callback
     *            回调
     */
    public void doGet(String url, HttpParams params, Callback callback) {

        String httpUrl = buildHttpUrl(url, params);
        Log.i("httpUrl = " + httpUrl);

        Request.Builder requestBuilder = new Request.Builder().url(httpUrl);
        Request request = requestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }


    /**
     * 拼接url
     *
     * @param url
     *            请求url
     * @param params
     *            请求参数
     * @return 拼接了参数的url
     */
    private String buildHttpUrl(String url, HttpParams params) {
        StringBuilder httpUrl = new StringBuilder(url);
        // 是否带有参数
        if (params != null) {
            // 反射得到参数对象
            Class<? extends HttpParams> clazz = params.getClass();
            // 获取参数对象所有属性
            Field fields[] = clazz.getDeclaredFields();
            httpUrl.append("?");
            for (Field field : fields) {
                // 突破private属性
                field.setAccessible(true);
                // 获取该字段的注解
                ParamField json = field.getAnnotation(ParamField.class);
                if (json != null && !TextUtils.isEmpty(json.value())) {
                    try {
                        httpUrl.append(json.value() + "="
                                + String.valueOf(field.get(params)) + "&");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (!TextUtils.isEmpty(token)) {
            if(httpUrl.toString().equals(url)){
                httpUrl.append("?");
            }
            httpUrl.append("token=" + token + "&");
        }

        if(httpUrl.toString().endsWith("&")){
            return httpUrl.toString().substring(0, httpUrl.length()-1);
        }

        return httpUrl.toString();
    }

}
