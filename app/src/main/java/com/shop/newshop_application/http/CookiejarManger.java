package com.shop.newshop_application.http;

import com.shop.newshop_application.constant.UrlConstant;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Unir|Superman
 * on 2017/11/22 10:30.
 * on Administrator
 * on NewShop_Application
 */

public class CookiejarManger implements CookieJar {
    /**
     * 保存cookie
     */
    private List<Cookie> mCookieList = new ArrayList<Cookie>();


    /**
     * 这里注册需要你保存Cookie的URL
     */
    private String COOKIE_URL[] = new String[]{
            UrlConstant.API

    };

    /**
     * 是否保存cookie
     *
     * @param url
     *            请求URL
     * @return true 保存
     */
    private boolean isSaveCookie(String url){
        for(String cookieUrl : COOKIE_URL){
            if(url.contains(cookieUrl)){
                return true;
            }
        }
        return false;
    }
    /**
     * Saves {@code cookies} from an HTTP response to this store according to this jar's policy.
     * <p>
     * <p>Note that this method may be called a second time for a single HTTP response if the response
     * includes a trailer. For this obscure HTTP feature, {@code cookies} contains only the trailer's
     * cookies.
     *
     * @param httpUrl
     * @param cookies
     */
    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> cookies) {
        String url = httpUrl.uri().toString();
        if(isSaveCookie(url)){
//    		mCookieList.clear(); // 这里只保存一次cookie
            mCookieList.addAll(cookies);
        }
    }

    /**
     * Load cookies from the jar for an HTTP request to {@code url}. This method returns a possibly
     * empty list of cookies for the network request.
     * <p>
     * <p>Simple implementations will return the accepted cookies that have not yet expired and that
     * {@linkplain Cookie#matches match} {@code url}.
     *
     * @param url
     */
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return mCookieList;
    }
}
