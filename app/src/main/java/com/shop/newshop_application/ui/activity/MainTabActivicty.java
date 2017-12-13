package com.shop.newshop_application.ui.activity;

import android.graphics.Color;
import android.view.KeyEvent;

import com.rhino.ui.utils.ActivityManager;
import com.shop.newshop_application.R;
import com.shop.newshop_application.application.MyApplication;
import com.shop.newshop_application.base.BaseHttpTabActivity;
import com.shop.newshop_application.ui.fragment.TabHomeFragment;
import com.shop.newshop_application.ui.fragment.TabMineFragment;
import com.shop.newshop_application.ui.fragment.TabNewFragment;
import com.shop.newshop_application.ui.fragment.TabShopFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Unir|Superman
 * on 2017/11/23 11:30.
 * on Administrator
 * on NewShop_Application
 */

public class MainTabActivicty extends BaseHttpTabActivity {
    public static final int TAB_INDEX_HOME = 0;
    public static final int TAB_INDEX_SHOP = 1;
    public static final int TAB_INDEX_NEW = 2;
    public static final int TAB_INDEX_MINE = 3;
    /**
     * 是否能退出app
     **/
    private boolean isExitEnable;
    @Override
    protected void initTabs() {
        addTab("Lol",new int[]{R.drawable.ic_shopping_cart_white_24dp},new TabShopFragment());
        addTab("新闻",new int[]{R.drawable.ic_fiber_new_white_24dp},new TabNewFragment());
        addTab("首页",new int[]{R.drawable.ic_home_white_24dp},new TabHomeFragment());
        addTab("我的",new int[]{R.drawable.ic_person_pin_white_24dp},new TabMineFragment());
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    @Override
    protected boolean initData() {
        return super.initData();
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    public void onTabsChanged(String tabId) {
        super.onTabsChanged(tabId);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doExit();
            return false;


        }
        return super.onKeyDown(keyCode, event);
    }
    private void doExit() {
        if (!isExitEnable) {
            isExitEnable = true;
            showAlert("再按一次将退出程序");
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    isExitEnable = false;
                }
            }, 2000);
        } else {
            ActivityManager.getInstance().Exit();
            releaseAll();
        }
    }
    /**
     * 释放内存
     **/
    private void releaseAll() {
        MyApplication.getInstance().getHttpRequest().setToken(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /**
     * 提供fragment调用，设置item的数字标识
     *
     * @param index tab索引
     * @param num   显示数量，大于0才显示
     */
    public void setTabItemNewFlag(int index, int num) {
        setNewFlag(index, num, 0 < num);
    }
}
