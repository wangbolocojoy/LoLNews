package com.shop.newshop_application.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import com.shop.newshop_application.adapter.FragmentHomelistAdapter;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.constant.UrlConstant;
import com.shop.newshop_application.http.result.taoutiaoresult.HomeVideoListInfo;
import com.shop.newshop_application.utils.helper.JsonHelper;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * Created by Unir|Superman
 * on 2017/11/23 11:09
 * on Administrator
 * on NewShop_Application
 */

public class TabHomeFragment extends BaseHttpFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private FragmentHomelistAdapter fragmentHomelistAdapter;
    private static int PAGESUM = 0;
    private List<HomeVideoListInfo.MsgBean.ResultBean> homeVideoListInfos;

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_home_fragment);
    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
        setTitleBackKeyVisible(false);
        GridLayoutManager gridlayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerview.setLayoutManager(gridlayoutManager);
        fragmentHomelistAdapter = new FragmentHomelistAdapter(null);
        fragmentHomelistAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerview.setAdapter(fragmentHomelistAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                PAGESUM = PAGESUM + 1;
                OkGo.<String>get(UrlConstant.LOLVIDEOAPI)
                        .params("page", PAGESUM)
                        .params("pagesize", 10)
                        .params("r1", 1)
                        .params("source", "lolapp")
                        .execute(new StringCallback() {
                            /**
                             * 对返回数据进行操作的回调， UI线程
                             *
                             * @param response
                             */
                            @Override
                            public void onSuccess(Response<String> response) {
                                HomeVideoListInfo info = JsonHelper.formatObj(response.body(), HomeVideoListInfo.class);
                                Log.d("info" + info.toString());
                                if (info != null) {
                                    homeVideoListInfos = new ArrayList<>();
                                    homeVideoListInfos = info.getMsg().getResult();
                                    fragmentHomelistAdapter.setNewData(homeVideoListInfos);
                                }
                            }
                        });
            }
        });
        refreshLayout.autoRefresh();
    }

}
