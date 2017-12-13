package com.shop.newshop_application.http.request.toutiao;


import com.shop.newshop_application.http.ParamField;

/**
 * Created by Unir|Superman
 * on 2017/12/1 16:37.
 * on Administrator
 * on NewShop_Application
 */

public class TouTiaoNewParams extends com.lzy.okgo.model.HttpParams {
    @ParamField("pageToken")
    private int pageToken;
    @ParamField("catid")
    private String catid;
    @ParamField("apikey")
    private String apikey;

    public TouTiaoNewParams( int pageToken, String catid) {
        this.pageToken = pageToken;
        this.catid = catid;
        this.apikey = "EpO0gJokdSVGPrxCzJycCPBeVgWwP4OeRxqSgmQv7s7r4tiEvrbysInEXKHf4nsD";
    }

    public int getPageToken() {
        return pageToken;
    }

    public void setPageToken(int pageToken) {
        this.pageToken = pageToken;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
}
