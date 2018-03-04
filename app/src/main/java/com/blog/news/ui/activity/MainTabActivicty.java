package com.blog.news.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;

import com.rhino.ui.utils.ActivityManager;
import com.blog.news.R;
import com.blog.news.application.MyApplication;
import com.blog.news.base.BaseHttpTabActivity;
import com.blog.news.constant.StringConstant;
import com.blog.news.ui.fragment.TabDouYInFragment;
import com.blog.news.ui.fragment.TabHomeFragment;
import com.blog.news.ui.fragment.TabShopFragment;
import com.blog.news.ui.fragment.TabShopMineFragment;
import com.blog.news.ui.fragment.TabShoppingCarFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Unir|Superman
 * on 2017/11/23 11:30.
 * on Administrator
 * on NewShop_Application
 */

public class MainTabActivicty extends BaseHttpTabActivity {
    public static int ShopSum;
    /**
     * 是否能退出app
     **/
    private boolean isExitEnable;
    @Override
    protected void initTabs() {
//        addTab(StringConstant.HOME,new int[]{R.drawable.ic_home_white_24dp},new TabHomeFragment());
        addTab(StringConstant.CLASSIFY,new int[]{R.drawable.ic_list},new TabShopFragment());
//        addTab(StringConstant.SHOPCAR,new int[]{R.drawable.ic_shopping_cart_white_24dp},new TabShoppingCarFragment());
        addTab(StringConstant.MINE,new int[]{R.drawable.ic_person_pin_white_24dp},new TabShopMineFragment());
//        addTab(StringConstant.DOUYIN,new int[]{R.drawable.ic_person_pin_white_24dp},new TabDouYInFragment());
    }
    public void setTab(int a){
        setCurrentTab(a);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
    protected void onResume() {
        super.onResume();
        setTabItemNewFlag(2,ShopSum);
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
            showAlert(StringConstant.OUTAPP);
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
