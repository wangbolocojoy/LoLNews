package com.shop.newshop_application.http.request.regster;

import com.lzy.okgo.model.HttpParams;
import com.shop.newshop_application.http.ParamField;

/**
 * Created by a on 2018/1/3.
 */

public class SmsCaptcha extends HttpParams {
    @ParamField("phone")
    private String phone;
    @ParamField("password")
    private String password;
    @ParamField("smsCaptcha")
    private String smsCaptcha;

    public SmsCaptcha(String phone, String smsCaptcha, String password) {
        this.phone = phone;
        this.smsCaptcha = smsCaptcha;
        this.password = password;
    }

    public SmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
