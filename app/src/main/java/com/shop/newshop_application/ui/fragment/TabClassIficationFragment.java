package com.shop.newshop_application.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author MacBookPor
 * @date 2018/1/10
 */

public class TabClassIficationFragment extends BaseHttpFragment {
    @BindView(R.id.tab_class_classification_item)
    ListView tabClassClassificationItem;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_new_fragment);
    }

    @Override
    protected boolean initData() {

        return true;
    }

    @Override
    protected void initView() {
        setTitle("分类");
        setTitleBackKeyVisible(false);
//        tabClassClassificationItem.setAdapter(new ArrayAdapter<String>());


    }
}
