package com.shop.newshop_application.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.ui.activity.news.ShowNewsActivity;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemAtlasFragment;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemComicFragment;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemGetUserFragment;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemNewsFragment;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemVideosFragment;
import com.shop.newshop_application.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Unir|Superman
 * on 2017/11/23 11:12.
 * on Administrator
 * on NewShop_Application
 */

public class TabShopFragment extends BaseHttpFragment {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    private List<String> imgList;

    private List<Pair<String, Fragment>> items;

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_shop_fragment);
    }

    @Override
    protected boolean initData() {
        items = new ArrayList<>();
        imgList=new ArrayList<>();
        initdata();
        return true;
    }


    @Override
    protected void initView() {
        setTitleVisible(false);
        setTitleBackKeyVisible(false);
        items.add(new Pair<String, Fragment>("新闻", new ItemNewsFragment()));
        items.add(new Pair<String, Fragment>("视频", new ItemVideosFragment()));
        items.add(new Pair<String, Fragment>("订阅号", new ItemGetUserFragment()));
        items.add(new Pair<String, Fragment>("漫画", new ItemComicFragment()));
        items.add(new Pair<String, Fragment>("图集", new ItemAtlasFragment()));
        viewPager.setAdapter(new MainAdapter(getChildFragmentManager()));
        tab.setupWithViewPager(viewPager);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(imgList);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.start();
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


    @OnClick({R.id.banner, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.banner:

                break;

            case R.id.fab:
                ShowNewsActivity.runActivity(getActivity(), "MyGithub", "https://github.com/wangbolocojoy");
                break;
            default:
                break;
        }
    }

    public  void initdata(){
        imgList.add("http://ossweb-img.qq.com/upload/adw/image/1513092514/1513092514.jpg?_r=1513219840");
        imgList.add("http://ossweb-img.qq.com/upload/adw/image/1513049171/1513049171.jpg?_r=1513219840");
        imgList.add("http://ossweb-img.qq.com/upload/adw/image/1513136055/1513136055.jpg?_r=1513219840");
        imgList.add("http://img.crawler.qq.com/lolwebschool/0/JAutoCMS_LOLWeb_e3d7b79f77788d4d6da840261e6c6775/0");



    }

    private class MainAdapter extends FragmentPagerAdapter {

        MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position).second;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position).first;
        }
    }
}
