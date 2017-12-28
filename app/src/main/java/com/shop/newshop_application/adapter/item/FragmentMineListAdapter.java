package com.shop.newshop_application.adapter.item;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shop.newshop_application.R;
import com.shop.newshop_application.glide.GlideApp;
import com.shop.newshop_application.http.result.taoutiaoresult.MineShopSumListInfo;
import com.shop.newshop_application.ui.activity.shoppingshow.ShopingDetailsActivity;
import com.shop.newshop_application.utils.GlideImageLoader;
import com.shop.newshop_application.views.CustomTextview;

import java.util.List;

/**
 * Created by u on 2017/12/20.
 */

public class FragmentMineListAdapter extends BaseQuickAdapter<MineShopSumListInfo, BaseViewHolder> {
    public FragmentMineListAdapter(@Nullable List<MineShopSumListInfo> data) {
        super(R.layout.liststyle_item_mine_list_data_item, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, MineShopSumListInfo item) {
        helper.setText(R.id.main_shop_name, item.getShopname());
        helper.setText(R.id.main_shopprice,String.valueOf(item.getShopsum()*item.getShopprice()));
        ImageView imageView=helper.getView(R.id.main_shop_imgurl);
        CustomTextview customTextview = helper.getView(R.id.main_shop_customtextview);
        GlideApp.with(mContext).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).load(item.getShopimgurl()).into(imageView);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopingDetailsActivity.runActivity(mContext,"1","123");
            }
        });
    }
}
