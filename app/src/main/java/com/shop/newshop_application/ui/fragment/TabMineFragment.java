package com.shop.newshop_application.ui.fragment;

import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;

/**
 * Created by Unir|Superman
 * on 2017/11/23 11:15.
 * on Administrator
 * on NewShop_Application
 */

public class TabMineFragment extends BaseHttpFragment {

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_mine_fragment);
    }


    @Override
    protected boolean initData() {
        return true;
    }


    @Override
    protected void initView() {
        setTitleBackKeyVisible(false);

    }
}
