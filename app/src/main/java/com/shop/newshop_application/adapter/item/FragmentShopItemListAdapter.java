package com.shop.newshop_application.adapter.item;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shop.newshop_application.R;
import com.shop.newshop_application.application.MyApplication;
import com.shop.newshop_application.glide.GlideApp;
import com.shop.newshop_application.http.result.tabshopitem.TabShopNewsListInfo;
import com.shop.newshop_application.ui.activity.news.ShowNewsActivity;

import java.util.List;

/**
 * Created by Unir|Superman
 * on 2017/12/7 17:24.
 * on Administrator
 * on NewShop_Application
 */

public class FragmentShopItemListAdapter extends BaseQuickAdapter<TabShopNewsListInfo.ListBean, BaseViewHolder> {

    public FragmentShopItemListAdapter(@Nullable List<TabShopNewsListInfo.ListBean> data) {
        super(R.layout.liststyle_shop__new_item_home_list_data, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final TabShopNewsListInfo.ListBean item) {
        helper.setText(R.id.tab_shop_new_list_item_titel, item.getTitle());
        helper.setText(R.id.tab_shop_new_list_item_user, item.getAuthor());
        GlideApp.with(mContext).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(item.getImage_url_small()).skipMemoryCache(true).into((ImageView) helper.getView(R.id.tab_shop_new_list_item_img));
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNewsActivity.runActivity(mContext, item.getTitle(), item.getArticle_url());
            }
        });
    }
}
