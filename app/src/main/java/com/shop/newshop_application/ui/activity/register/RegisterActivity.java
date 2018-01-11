package com.shop.newshop_application.ui.activity.register;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.rhino.ui.utils.Log;
import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpActivity;
import com.shop.newshop_application.constant.UrlConstant;
import com.shop.newshop_application.http.request.regster.SmsCaptcha;
import com.shop.newshop_application.http.result.BaseResult;
import com.shop.newshop_application.utils.helper.JsonHelper;
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
    private String title;
    private String rebuttontext;

    @Override
    protected void setContent() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected boolean initData() {
        title = mExtras.getString("t");
        rebuttontext = mExtras.getString("d");
        return true;
    }

    @Override
    protected void initView() {
        setTitleBackKeyVisible(false);
        setTitle(title);
        inButterKnifeView();
        reRegisterButton.setText(rebuttontext);
    }


    @OnClick({R.id.re_register_post_yzm, R.id.re_register_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.re_register_post_yzm:
                SendPhoneMessage(reRegisterPhone.getText().toString());
                break;
            case R.id.re_register_button:
                Registerbyphone(reRegisterPhone.getText().toString(),reRegisterYzm.getText().toString(),reRegisterPassword.getText().toString());
                break;
            default:
                break;
        }
    }
    public boolean CheckPhoneNumber(String Phone){
        if (!TextUtils.isEmpty(Phone)&&Phone.length()==11&&RegexUtils.isMobileSimple(Phone)){
            Log.d("手机号码正确");
           return true;
        }
           showAlert("请检查你的手机号码");
        return false;
    }
    public void SendPhoneMessage(String Phone){
        if (CheckPhoneNumber(Phone)){
            if (checkNetWork()){
                Log.d("SendPhoneMessage");
                Log.d(Phone);
                OkGo.<String>post(UrlConstant.REGISTERSENDMES)
                        .params("phone",Phone)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                BaseResult baseResult=JsonHelper.formatObj(response.body(),BaseResult.class);
                                Log.d(baseResult.toString());
                                showAlert(baseResult.getMsg());
                            }
                        });

            }

        }else {

        }

    }
    public void Registerbyphone(String phone, String mes, String password){
        if (mes.length()==6&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(phone)){
            if (!checkNetWork()){

                OkGo.<String>post(UrlConstant.REGISTERBYPHONE)
                        .params(new SmsCaptcha(phone,mes,password))
                        .execute(new StringCallback() {

                            @Override
                            public void onSuccess(Response<String> response) {
                                BaseResult baseResult=JsonHelper.formatObj(response.body(),BaseResult.class);
                                Log.d("onSuccess"+baseResult.toString());
                                if (baseResult.getStatus()==BaseResult.RS_OK){
                                    showAlert("注册成功");
                                    finish();
                                }
                            }
                        });


            }
        }else {
            showAlert("不能为空");
        }
    }







}
