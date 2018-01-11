package com.shop.newshop_application.ui.fragment;

import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;

import java.util.List;

/**
 *
 * @author a
 * @date 2017/12/29
 */

public class TabShoppingCarFragment  extends BaseHttpFragment{

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_shoppingcar_fragment);

    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        setTitle("购物车");
        setTitleBackKeyVisible(false);


    }
}
