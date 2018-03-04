package com.blog.news.adapter.tabhomeitem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blog.news.R;
import com.blog.news.application.MyApplication;
import com.blog.news.glide.GlideApp;
import com.blog.news.http.result.home.Home_Json_Bean;
import com.blog.news.ui.activity.SerchShopPingActivity;
import com.blog.news.ui.activity.shoppingshow.ShopingDetailsActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author MacBookPor
 * @date 2018/1/5
 */
public class Bean1ViewBinder extends ItemViewBinder<Home_Json_Bean.DatasBean.bean1, Bean1ViewBinder.ViewHolder> {
    private int i = 0;

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_bean1, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull final Home_Json_Bean.DatasBean.bean1 bean1) {
        List<String> imgurl = new ArrayList<>();
        final List<String> shopingid = new ArrayList<>();
        for (i = 0; i < bean1.getAdv_list().getItem().size(); i++) {
            imgurl.add(bean1.getAdv_list().getItem().get(i).getImage());
            shopingid.add(bean1.getAdv_list().getItem().get(i).getData());

        }
        holder.banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GlideApp.with(context).load(path).into(imageView);
            }
        });
        holder.banner.setImages(imgurl);
        holder.banner.start();
        holder.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (shopingid.get(position).contains("product_list")) {
                    String params = shopingid.get(position).substring(50);
                    SerchShopPingActivity.runActivity(MyApplication.getContext(), params);
                } else {
                    String params = shopingid.get(position).substring(59);
                    ShopingDetailsActivity.runActivity(MyApplication.getContext(), params);
                }
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private Home_Json_Bean.DatasBean.bean1 bean1;
        private Banner banner;

        ViewHolder(View itemView) {
            super(itemView);
            this.banner = (Banner) itemView.findViewById(R.id.home_banner_item_bannersd);
        }
    }
}
