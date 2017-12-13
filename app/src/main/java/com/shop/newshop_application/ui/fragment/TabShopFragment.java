package com.shop.newshop_application.ui.fragment;

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

import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.ui.activity.news.ShowNewsActivity;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemAtlasFragment;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemComicFragment;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemGetUserFragment;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemNewsFragment;
import com.shop.newshop_application.ui.fragment.itemfragment.ItemVideosFragment;

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

    private List<Pair<String, Fragment>> items;

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_shop_fragment);
    }

    @Override
    protected boolean initData() {
        items = new ArrayList<>();


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

    @OnClick(R.id.fab)
    public void onViewClicked() {
        ShowNewsActivity.runActivity(getActivity(),"MyGithub","https://github.com/wangbolocojoy");
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
