package com.shop.newshop_application.ui.activity.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpActivity;
import com.shop.newshop_application.views.ClearEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseHttpActivity {


    @BindView(R.id.re_register_phone)
    ClearEditText reRegisterPhone;
    @BindView(R.id.re_register_yzm)
    ClearEditText reRegisterYzm;
    @BindView(R.id.re_register_post_yzm)
    TextView reRegisterPostYzm;
    @BindView(R.id.re_register_password)
    ClearEditText reRegisterPassword;
    @BindView(R.id.re_register_button)
    Button reRegisterButton;

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
        inButterKnifeView();
    }


    @OnClick({R.id.re_register_post_yzm, R.id.re_register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.re_register_post_yzm:
                break;
            case R.id.re_register_button:
                break;
        }
    }
}
