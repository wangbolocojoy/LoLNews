package com.shop.newshop_application.http.request.login;

import com.lzy.okgo.model.HttpParams;
import com.shop.newshop_application.http.ParamField;

/**
 * Created by a on 2018/1/3.
 */

public class LoginByPhone extends HttpParams {
    @ParamField("username")
    private String username;
    @ParamField("password")
    private String password;

    public LoginByPhone(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
