package com.shop.newshop_application.ui.fragment.itemfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rhino.ui.utils.Log;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.shop.FragmentShopItemListAdapter;
import com.shop.newshop_application.base.LazyLoadFragment;
import com.shop.newshop_application.constant.UrlConstant;
import com.shop.newshop_application.http.result.tabshopitem.TabShopNewsListInfo;
import com.shop.newshop_application.utils.helper.JsonHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Unir|Superman
 * on 2017/12/7 14:52.
 * on Administrator
 * on NewShop_Application
 */

public class ItemNewsFragment extends LazyLoadFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private FragmentShopItemListAdapter fragmentShopItemListAdapter;
    private static int PAGESUM = 0;
    private List<TabShopNewsListInfo.ListBean> listBeanList;

    @Override
    protected void setContent() {
        setContentView(R.layout.item_news_fragmet_items);
    }

    @Override
    protected boolean initData() {
        return true;
    }

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
                getInfo();
            }
        });

    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    public void lazyLoad() {
        refreshLayout.autoRefresh();
    }

    /**
     * 联网获取数据
     */
    @Override
    public void getInfo() {
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
                            if (listBeanList!=null){
                                Log.d("新闻条数"+listBeanList.size());
                                fragmentShopItemListAdapter.setNewData(listBeanList);
                            }

                        }
                    }
                });
    }

}
