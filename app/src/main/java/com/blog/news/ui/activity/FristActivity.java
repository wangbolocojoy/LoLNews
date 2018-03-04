package com.blog.news.ui.activity;

import android.app.Activity;
import android.os.Handler;


import com.blog.news.R;
import com.blog.news.base.BaseHttpActivity;
import com.blog.news.ui.activity.register.LoginActivity;
import com.blog.news.utils.UiUtils;

public class FristActivity extends BaseHttpActivity {


    private Handler postHandle = new Handler();
    private Runnable postRunnable = new Runnable() {
        @Override
        public void run() {
            gotMainPage(FristActivity.this);
//        gotoActivity(LoginActivity.class);
        }
    };

    @Override
    protected void setContent() {
        setContentView(R.layout.activity_frist);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
        showNextPage(1000);
    }


    /**
     * 默认自动登陆
     */
    private void dologin(){

    }
    /**
     * 跳转到下一个页面
     **/
    private void showNextPage(int delayTime) {
        postHandle.removeCallbacks(postRunnable);
        postHandle.postDelayed(postRunnable, delayTime);
    }
    /**
     * 跳转到主页
     **/
    private void gotMainPage(Activity activity) {
        UiUtils.showMainPage(activity, mExtras);
    }


}
