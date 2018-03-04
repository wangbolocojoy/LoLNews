package com.blog.news.ui.fragment.shopping;

import com.blog.news.R;
import com.blog.news.base.BaseHttpFragment;

/**
 *
 * @author a
 * @date 2017/12/28
 */

public class ShoppingCommodityFragment extends BaseHttpFragment {
    @Override
    protected void setContent() {
        setContentView(R.layout.fragment_shopping_commodity);
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
