package com.shop.newshop_application.ui.fragment.itemfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.shop.newshop_application.adapter.item.FragmentShopItemListAdapter;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.constant.UrlConstant;
import com.shop.newshop_application.http.result.tabshopitem.TabShopNewsListInfo;
import com.shop.newshop_application.utils.helper.JsonHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Unir|Superman
 * on 2017/12/7 14:55.
 * on Administrator
 * on NewShop_Application
 */

public class ItemComicFragment extends BaseHttpFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private FragmentShopItemListAdapter fragmentShopItemListAdapter;
    private static int PAGESUM = 0;
    private List<TabShopNewsListInfo.ListBean> listBeanList;
    /**
     * set the parent view
     * {@link #setContentView(int)}
     * {@link #setContentView(View)}}
     */
    @Override
    protected void setContent() {
        setContentView(R.layout.item_comic_fragmet_items);
    }

    /**
     * init the data
     *
     * @return true success, false failed
     */
    @Override
    protected boolean initData() {
        return true;
    }

    /**
     * init the view
     */
    @Override
    protected void initView() {
        setTitleVisible(false);
        setTitleBackKeyVisible(false);
        GridLayoutManager gridlayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerview.setLayoutManager(gridlayoutManager);
        fragmentShopItemListAdapter = new FragmentShopItemListAdapter(null);
        fragmentShopItemListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerview.setAdapter(fragmentShopItemListAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                PAGESUM = PAGESUM + 1;
                OkGo.<String>get(UrlConstant.LOLNEWSAPI)
                        .params("id", 16)
                        .params("page", PAGESUM)
                        .params("plat", "android")
                        .params("version", "9689")
                        .execute(new StringCallback() {
                            /**
                             * 对返回数据进行操作的回调， UI线程
                             *
                             * @param response
                             */
                            @Override
                            public void onSuccess(Response<String> response) {
                                TabShopNewsListInfo info = JsonHelper.formatObj(response.body(), TabShopNewsListInfo.class);
                                Log.d("info" + info.toString());
                                if (info != null) {
                                    listBeanList = new ArrayList<>();
                                    listBeanList = info.getList();
                                    fragmentShopItemListAdapter.setNewData(listBeanList);
                                }
                            }
                        });

            }
        });
        refreshLayout.autoRefresh();
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
