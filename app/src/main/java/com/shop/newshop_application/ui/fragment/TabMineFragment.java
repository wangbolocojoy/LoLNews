package com.shop.newshop_application.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.shop.FragmentMineListAdapter;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.constant.UrlConstant;
import com.shop.newshop_application.http.result.mine.MineShopSumListInfo;
import com.shop.newshop_application.utils.helper.JsonHelper;

import butterknife.BindView;

/**
 * Created by Unir|Superman
 * on 2017/11/23 11:15.
 * on Administrator
 * on NewShop_Application
 */

public class TabMineFragment extends BaseHttpFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    FragmentMineListAdapter fragmentMineListAdapter;
    private MineShopSumListInfo shopSumListInfos;

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
        GridLayoutManager gridlayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridlayoutManager);
        fragmentMineListAdapter = new FragmentMineListAdapter(null);
        fragmentMineListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerview.setAdapter(fragmentMineListAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                getallshoppinginfo();
            }
        });
        refreshLayout.autoRefresh();
    }

    public void getallshoppinginfo() {
        if (!checkNetWork()) {
            OkGo.<String>post(UrlConstant.CHECKALLSHOPPINGLIST)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            MineShopSumListInfo shopSumListInfos = JsonHelper.formatObj(response.body(), MineShopSumListInfo.class);
                            if (shopSumListInfos.getData() != null) {
                                fragmentMineListAdapter.setNewData(shopSumListInfos.getData());

                            }else {
                                fragmentMineListAdapter.setEmptyView(getView());
                            }

                        }
                    });
        }
    }


}
