package com.shop.newshop_application.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rhino.ui.utils.Log;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.FragmentNewsListAdapter;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.constant.UrlConstant;
import com.shop.newshop_application.http.result.taoutiaoresult.TouTiaoListInfo;
import com.shop.newshop_application.utils.helper.JsonHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Created by Unir|Superman
 * on 2017/11/23 11:14.
 * on Administrator
 * on NewShop_Application
 */

public class TabNewFragment extends BaseHttpFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private FragmentNewsListAdapter fragmentNewsListAdapter;
    private List<TouTiaoListInfo.DataBean> newslist;
    private static int PAGESUM = 0;

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
        setTitle("头条");
        setTitleBackKeyVisible(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        fragmentNewsListAdapter = new FragmentNewsListAdapter(null);
        fragmentNewsListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerview.setAdapter(fragmentNewsListAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                PAGESUM = PAGESUM + 1;
                OkGo.<String>get(UrlConstant.API)
                        .params("pageToken", PAGESUM)
                        .params("kw", "英雄联盟")
                        .params("apikey", "EpO0gJokdSVGPrxCzJycCPBeVgWwP4OeRxqSgmQv7s7r4tiEvrbysInEXKHf4nsD")
                        .execute(new StringCallback() {
                            /**
                             * 对返回数据进行操作的回调， UI线程
                             *
                             * @param response
                             */
                            @Override
                            public void onSuccess(Response<String> response) {
                                TouTiaoListInfo info = JsonHelper.formatObj(response.body(), TouTiaoListInfo.class);
                                Log.d("info" + info.toString());
                                if (info != null) {
                                    newslist = new ArrayList<>();
                                    Log.d(info.getData().get(0).toString());
                                    newslist = info.getData();
                                    fragmentNewsListAdapter.setNewData(newslist);
                                }
                            }
                        });

            }
        });
        refreshLayout.autoRefresh();
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Log.d("新位置"+newState);
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                if (dy>0){
//                getTitleView().setVisibility(View.GONE);
//                }else {
//                    getTitleView().setVisibility(View.VISIBLE);
//                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
