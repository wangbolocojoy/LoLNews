package com.shop.newshop_application.adapter;

import android.app.Application;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rhino.ui.utils.Log;
import com.shop.newshop_application.R;
import com.shop.newshop_application.application.MyApplication;
import com.shop.newshop_application.glide.GlideApp;
import com.shop.newshop_application.http.result.taoutiaoresult.HomeVideoListInfo;
import com.shop.newshop_application.http.result.taoutiaoresult.HomeVideoListInfo.MsgBean.ResultBean;
import com.shop.newshop_application.ui.activity.news.ShowNewsActivity;

import java.util.List;

/**
 * Created by Unir|Superman
 * on 2017/12/6 14:34.
 * on Administrator
 * on NewShop_Application
 */

public class FragmentHomelistAdapter extends BaseQuickAdapter<ResultBean,BaseViewHolder> {
    public FragmentHomelistAdapter(@Nullable List<ResultBean> data) {
        super(R.layout.liststyle_item_home_list_data,data);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, final ResultBean item) {
        helper.setText(R.id.home_video_item_tetil,item.getTitle());
        helper.setText(R.id.home_video_item_video_time,item.getTime());
        ImageView imageView=helper.getView(R.id.home_video_item_img);
        GlideApp.with(mContext).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(item.getAppthumb()).skipMemoryCache(true).into((ImageView) helper.getView(R.id.home_video_item_img));
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                ShowNewsActivity.runActivity(mContext,item.getTitle(),item.getAppurl());
            }
        });



    }
}
