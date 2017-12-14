package com.shop.newshop_application.ui.activity;

import android.app.Activity;
import android.os.Handler;
import android.widget.Button;

import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpActivity;
import com.shop.newshop_application.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FristActivity extends BaseHttpActivity {


    private Handler postHandle = new Handler();
    private Runnable postRunnable = new Runnable() {
        @Override
        public void run() {
            gotMainPage(FristActivity.this);
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
    ButterKnife.bind(this);
    setTitleBackKeyVisible(false);
        showNextPage(2000);
    }
    /** 跳转到下一个页面 **/
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
