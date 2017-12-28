package com.shop.newshop_application.ui.activity.shoppingshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxz.PagerSlidingTabStrip;
import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.shop.ItemTitlePagerAdapter;
import com.shop.newshop_application.base.BaseHttpActivity;
import com.shop.newshop_application.ui.activity.news.ShowNewsActivity;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingCommodityFragment;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingDetailsFragment;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingInfoFragment;
import com.shop.newshop_application.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopingDetailsActivity extends AppCompatActivity {

    public final static String URL = "url";
    public final static String TITLE = "title";

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.psts_tabs)
    PagerSlidingTabStrip pstsTabs;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_title_root)
    LinearLayout llTitleRoot;
    @BindView(R.id.shop_viewpager)
    NoScrollViewPager shopViewpager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ShoppingCommodityFragment shoppingCommodityFragment;
    private ShoppingDetailsFragment shoppingDetailsFragment;
    private ShoppingInfoFragment shoppingInfoFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_details);
        ButterKnife.bind(this);
        fragmentList.add(shoppingInfoFragment = new ShoppingInfoFragment());
        fragmentList.add(shoppingDetailsFragment = new ShoppingDetailsFragment());
        fragmentList.add(shoppingCommodityFragment = new ShoppingCommodityFragment());
        shopViewpager.setAdapter(new ItemTitlePagerAdapter(getSupportFragmentManager(),
                fragmentList, new String[]{"商品", "详情", "评价"}));
        shopViewpager.setOffscreenPageLimit(3);
        pstsTabs.setViewPager(shopViewpager);

    }


    public static void runActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, ShopingDetailsActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }
}
