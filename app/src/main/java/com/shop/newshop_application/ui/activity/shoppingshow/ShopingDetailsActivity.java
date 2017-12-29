package com.shop.newshop_application.ui.activity.shoppingshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxz.PagerSlidingTabStrip;
import com.rhino.ui.utils.Log;
import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.shop.ItemTitlePagerAdapter;
import com.shop.newshop_application.base.BaseHttpActivity;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingCommodityFragment;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingDetailsFragment;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingInfoFragment;
import com.shop.newshop_application.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopingDetailsActivity extends BaseHttpActivity {

    public final static String URL = "url";
    public final static String TITLE = "title";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.psts_tabs)
    public
    PagerSlidingTabStrip pstsTabs;
    @BindView(R.id.tv_title)
    public
    TextView tvTitle;
    @BindView(R.id.ll_title_root)
    LinearLayout llTitleRoot;
    @BindView(R.id.shop_details_vpager)
    public
    NoScrollViewPager shopDetailsVpager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ShoppingCommodityFragment shoppingCommodityFragment;
    private ShoppingDetailsFragment shoppingDetailsFragment;
    private ShoppingInfoFragment shoppingInfoFragment;
    private ItemTitlePagerAdapter itemTitlePagerAdapter;

    @Override
    protected void setContent() {
        setContentView(R.layout.activity_shoping_details);
//        Slidr.attach(this);
    }

    @Override
    protected boolean initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(shoppingInfoFragment = new ShoppingInfoFragment());
        fragmentList.add(shoppingDetailsFragment = new ShoppingDetailsFragment());
        fragmentList.add(shoppingCommodityFragment = new ShoppingCommodityFragment());
        itemTitlePagerAdapter = new ItemTitlePagerAdapter(getSupportFragmentManager(), fragmentList, new String[]{"商品", "详情", "评价"});
        return true;
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    @Override
    protected void initView() {
        inButterKnifeView();
        setTitleVisible(false);
        shopDetailsVpager = findSubViewById(R.id.shop_details_vpager);
        pstsTabs = findSubViewById(R.id.psts_tabs);
        shopDetailsVpager.setAdapter(itemTitlePagerAdapter);
        shopDetailsVpager.setOffscreenPageLimit(3);
        pstsTabs.setViewPager(shopDetailsVpager);
//        baseOnClickListener(ivBack);
        Log.d("details_Activity");
    }


    public static void runActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, ShopingDetailsActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }




    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
