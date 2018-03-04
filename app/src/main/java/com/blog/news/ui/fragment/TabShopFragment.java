package com.blog.news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.blog.news.R;
import com.blog.news.base.BaseHttpFragment;
import com.blog.news.constant.UrlConstant;
import com.blog.news.glide.GlideApp;
import com.blog.news.http.result.blog.NewsType;
import com.blog.news.ui.fragment.blog.BlogNewsFragment;
import com.blog.news.utils.helper.JsonHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.rhino.ui.utils.Log;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

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
 *
 * @author MacBookPor
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
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.adView)
    AdView mAdView;
    private List<String> imgList;
    private NewsType newsType;
    private List<Pair<String, Fragment>> items;
    private List<NewsType.CategoriesBean> newstype;
    BlogNewsFragment blogNewsFragment;

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_shop_fragment);
    }

    @Override
    protected boolean initData() {
        items = new ArrayList<>();
        imgList = new ArrayList<>();
        newstype = new ArrayList<>();
        blogNewsFragment = new BlogNewsFragment(2);
        return true;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
        setTitleBackKeyVisible(false);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GlideApp.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(imgList);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.start();
        checkNewsNum();
    }


    @OnClick({R.id.banner, R.id.fab, R.id.tab, R.id.viewPager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.banner:
                break;
            case R.id.fab:
//                ShowNewsActivity.runActivity(getActivity(), "MyGithub", "https://github.com/wangbolocojoy");
                break;
            default:
                break;
        }
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
        public Parcelable saveState() {

            return super.saveState();
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


    public void checkNewsNum() {
        if (checkNetWork()) {
            doPost(UrlConstant.NEWS_TYPE);
        }

    }

    @Override
    protected void CallbackHandler(int event, String url, String data, Object reqData) {
        super.CallbackHandler(event, url, data, reqData);
        switch (url) {
            case UrlConstant.NEWS_TYPE:
                getNewsTypeNum(event, data);
                break;

            default:
                break;
        }
    }

    /**
     * @param event 验证码
     * @param data  数据
     */
    private void getNewsTypeNum(int event, String data) {
        if (!isHttpRequestSuccess(event)) {
            Log.d("获取数据失败");
        } else {
            Log.d("获取数据成功");
            newsType = JsonHelper.formatObj(data, NewsType.class);
            if (newsType != null) {
                for (int i = 0; i < newsType.getCategories().size(); i++) {
                    items.add(new Pair<String, Fragment>(newsType.getCategories().get(i).getTitle(), new BlogNewsFragment(newsType.getCategories().get(i).getId())));
                }
                viewPager.setAdapter(new MainAdapter(getChildFragmentManager()));
                tab.setupWithViewPager(viewPager, true);
                viewPager.setOffscreenPageLimit(5);
            }
        }

    }


}
