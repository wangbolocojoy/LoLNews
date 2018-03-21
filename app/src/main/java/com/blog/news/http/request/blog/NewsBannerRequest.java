package com.blog.news.http.request.blog;

import com.blog.news.http.ParamField;
import com.blog.news.http.request.HttpParams;

/**
 * Created by SuperDaHuiLang on 2018/3/12.
 */

public class NewsBannerRequest implements HttpParams{
    @ParamField("id")
    private int id;
    @ParamField("page")
    private int page;
    @ParamField("plat")
    private String plat;
    @ParamField("version")
    private String version;

    public NewsBannerRequest(int id, int page, String plat, String version) {
        this.id = id;
        this.page = page;
        this.plat = plat;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public String getPlat() {
        return plat;
    }

    public String getVersion() {
        return version;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
