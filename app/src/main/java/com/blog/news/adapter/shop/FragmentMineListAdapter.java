package com.blog.news.adapter.shop;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rhino.ui.utils.Log;
import com.blog.news.R;
import com.blog.news.glide.GlideApp;
import com.blog.news.http.result.mine.MineShopSumListInfo;
import com.blog.news.ui.activity.shoppingshow.ShopingDetailsActivity;
import com.blog.news.views.CustomTextview;

import java.util.List;

/**
 * Created by u on 2017/12/20.
 */

public class FragmentMineListAdapter extends BaseQuickAdapter<MineShopSumListInfo.DataBean, BaseViewHolder> {
    public FragmentMineListAdapter(@Nullable List<MineShopSumListInfo.DataBean> data) {
        super(R.layout.liststyle_item_mine_list_data_item, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, MineShopSumListInfo.DataBean item) {
        helper.setText(R.id.main_shop_name, item.getTitle());
        helper.setText(R.id.main_shopprice,String.valueOf(item.getPrice()));
        ImageView imageView=helper.getView(R.id.main_shop_imgurl);
        CustomTextview customTextview = helper.getView(R.id.main_shop_customtextview);
        GlideApp.with(mContext).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).load(item.getImage()).into(imageView);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("gotodetaila-----ctivity");
                ShopingDetailsActivity.runActivity(mContext,"1");
            }
        });
    }
}
