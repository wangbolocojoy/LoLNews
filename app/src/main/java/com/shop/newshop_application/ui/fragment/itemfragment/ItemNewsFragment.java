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
 * on 2017/12/7 14:52.
 * on Administrator
 * on NewShop_Application
 */

public class ItemNewsFragment extends BaseHttpFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private FragmentShopItemListAdapter fragmentShopItemListAdapter;
    private static int PAGESUM = 0;
    private List<TabShopNewsListInfo.ListBean> listBeanList;
    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    @Override
    protected void setContent() {
        setContentView(R.layout.item_news_fragmet_items);
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
        GridLayoutManager gridlayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridlayoutManager);
        fragmentShopItemListAdapter = new FragmentShopItemListAdapter(null);
        fragmentShopItemListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerview.setAdapter(fragmentShopItemListAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                getinfo();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        isInit = true;
        /**初始化的时候去加载数据**/
        isCanLoadData();
        return rootView;
    }
    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }
    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    public  void lazyLoad(){
        refreshLayout.autoRefresh();
    };

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isInit = false;
        isLoad = false;
    }
    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    public void getinfo(){
        PAGESUM = PAGESUM + 1;
        OkGo.<String>get(UrlConstant.LOLNEWSAPI)
                .params("id", 10)
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


}
