package com.blog.news.http.request.blog;

import com.blog.news.http.ParamField;
import com.blog.news.http.request.HttpParams;

/**
 * Created by MacBookPor on 2018/2/24.
 */

public class NewsRequest implements HttpParams{
    @ParamField("id")
    private int  id;

    public NewsRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
