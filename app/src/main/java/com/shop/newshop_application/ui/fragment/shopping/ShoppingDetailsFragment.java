package com.shop.newshop_application.ui.fragment.shopping;

import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.base.LazyLoadFragment;

/**
 *
 * @author a
 * @date 2017/12/28
 */

public class ShoppingDetailsFragment extends BaseHttpFragment {
    @Override
    protected void setContent() {
        setContentView(R.layout.fragment_shopping_details);
    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
        setTitleBackKeyVisible(false);

    }


}
