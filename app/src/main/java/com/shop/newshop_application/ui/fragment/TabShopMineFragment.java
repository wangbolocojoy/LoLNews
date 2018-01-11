package com.shop.newshop_application.ui.fragment;

import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;

/**
 *
 * @author a
 * @date 2018/1/2
 */

public class TabShopMineFragment extends BaseHttpFragment {
    @Override
    protected void setContent() {
        setContentView(R.layout.tab_shop_mine_fragment);

    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);

    }
}
