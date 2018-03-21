package com.blog.news.ui.fragment.used;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.blog.news.R;
import com.blog.news.adapter.shop.FragmentMineListAdapter;
import com.blog.news.base.BaseHttpFragment;
import com.blog.news.constant.UrlConstant;
import com.blog.news.http.result.mine.MineShopSumListInfo;
import com.blog.news.utils.helper.JsonHelper;

import butterknife.BindView;

/**
 * Created by Unir|Superman
 * on 2017/11/23 11:15.
 * on Administrator
 * on NewShop_Application
 */

public class TabBlogMineFragment extends BaseHttpFragment {


    @Override
    protected void setContent() {
        setContentView(R.layout.tab_blog_mine_fragment);
    }
    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        setTitle("mine");
        setTitleBackKeyVisible(false);

    }



}
