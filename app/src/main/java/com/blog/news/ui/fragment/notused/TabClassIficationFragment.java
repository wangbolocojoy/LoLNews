package com.blog.news.ui.fragment.notused;

import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.blog.news.R;
import com.blog.news.base.BaseHttpFragment;

import butterknife.BindView;

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
