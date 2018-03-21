package com.blog.news.http.result.blog;


import com.blog.news.http.ParamField;
import com.blog.news.http.request.HttpParams;

/**
 *
 * @author SuperDaHuiLang
 * @date 2018/3/11
 */

public class Comment  implements HttpParams {
    @ParamField("post_id")
    private int post_id;
    @ParamField("name")
    private String name;
    @ParamField("email")
    private String email;
    @ParamField("content")
    private String content;

    public Comment(int post_id, String name, String email, String comment) {
        this.post_id = post_id;
        this.name = name;
        this.email = email;
        this.content = comment;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}



