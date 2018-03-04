package com.blog.news.ui.activity.register;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blog.news.R;
import com.blog.news.base.BaseHttpActivity;
import com.blog.news.constant.StringConstant;
import com.blog.news.utils.UiUtils;
import com.blog.news.views.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseHttpActivity {

    @BindView(R.id.login_phone)
    ClearEditText loginPhone;
    @BindView(R.id.login_password)
    ClearEditText loginPassword;
    @BindView(R.id.login_froget_password)
    TextView loginFrogetPassword;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.login_regist_newid)
    TextView loginRegistNewid;
    private String title;
    private String button;

    @Override
    protected void setContent() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        setTitle(StringConstant.LOGINTITEL);
        setTitleBackKeyVisible(false);
        inButterKnifeView();

    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    @OnClick({R.id.login_froget_password, R.id.login_button, R.id.login_regist_newid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_froget_password:
                title = StringConstant.CHANGEPASSWORD;
                button = StringConstant.CONFIRMPASSWORD;
                mExtras.putString("t", title);
                mExtras.putString("d", button);
                gotoActivity(RegisterActivity.class, mExtras);

                break;
            case R.id.login_button:
                gotMainPage(this);

                break;
            case R.id.login_regist_newid:
                title = StringConstant.REGISTERTITLE;
                button = StringConstant.REGISTERTITLE;
                mExtras.putString("t", title);
                mExtras.putString("d", button);
                gotoActivity(RegisterActivity.class, mExtras);
                break;
            default:
                break;

        }
    }


    /**
     * 跳转到主页
     **/
    private void gotMainPage(Activity activity) {
        UiUtils.showMainPage(activity, mExtras);
    }

}

