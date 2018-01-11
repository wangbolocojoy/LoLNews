package com.shop.newshop_application.adapter.tabhomeitem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rhino.ui.utils.Log;
import com.shop.newshop_application.R;
import com.shop.newshop_application.application.MyApplication;
import com.shop.newshop_application.glide.GlideApp;
import com.shop.newshop_application.http.result.home.Home_Json_Bean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by MacBookPor on 2018/1/6.
 */
public class Bean3ViewBinder extends ItemViewBinder<Home_Json_Bean.DatasBean.bean2.Home1Bean, Bean3ViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_bean2, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Home_Json_Bean.DatasBean.bean2.Home1Bean bean2) {
        if (!bean2.getImage().isEmpty()) {
            Log.d("null");
        }
        holder.bean2web.loadUrl(bean2.getData());
        GlideApp.with(MyApplication.getContext()).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(bean2.getImage()).skipMemoryCache(true).into(holder.bean2img);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView bean2img;
        private WebView bean2web;

        ViewHolder(View itemView) {

            super(itemView);
            bean2img = (ImageView) itemView.findViewById(R.id.home_bean2_item_imgv);
            bean2web = (WebView) itemView.findViewById(R.id.home_bean2_item_webv);
        }
    }
}
