package com.shop.newshop_application.ui.activity.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpActivity;

public class RegisterActivity extends BaseHttpActivity {


    @Override
    protected void setContent() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected boolean initData() {
        return true;
        }

    @Override
    protected void initView() {


    }
}
