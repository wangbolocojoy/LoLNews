package com.shop.newshop_application.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.item.FragmentMineListAdapter;
import com.shop.newshop_application.adapter.item.FragmentShopItemListAdapter;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.http.result.taoutiaoresult.MineShopSumListInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private List<MineShopSumListInfo> shopSumListInfos;

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_mine_fragment);
    }


    @Override
    protected boolean initData() {
        shopSumListInfos=new ArrayList<>();
        shopSumListInfos.add(new MineShopSumListInfo("http://img009.hc360.cn/m4/M07/03/3D/wKhQ6FRv8jGEPE0lAAAAAPdxVI0914.jpg","西瓜",1,36));
        shopSumListInfos.add(new MineShopSumListInfo("http://img004.hc360.cn/g1/M01/92/39/wKhQMVMCye2EVuKhAAAAADICUqM642.jpg","橙子",5,70));
        shopSumListInfos.add(new MineShopSumListInfo("http://img009.hc360.cn/m4/M07/03/3D/wKhQ6FRv8jGEPE0lAAAAAPdxVI0914.jpg","苹果",8,25));
        shopSumListInfos.add(new MineShopSumListInfo("http://img009.hc360.cn/m4/M07/03/3D/wKhQ6FRv8jGEPE0lAAAAAPdxVI0914.jpg","草莓",15,18));
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
        fragmentMineListAdapter.addData(shopSumListInfos);
    }



}
