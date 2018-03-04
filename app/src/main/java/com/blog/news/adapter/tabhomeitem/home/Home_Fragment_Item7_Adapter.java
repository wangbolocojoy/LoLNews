package com.blog.news.adapter.tabhomeitem.home;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.blog.news.R;
import com.blog.news.glide.GlideApp;
import com.blog.news.http.result.home.Home_Json_Bean;
import com.blog.news.ui.activity.shoppingshow.ShopingDetailsActivity;

import java.util.List;

/**
 *
 * @author MacBookPor
 * @date 2018/1/8
 */

public class Home_Fragment_Item7_Adapter extends BaseQuickAdapter<Home_Json_Bean.DatasBean.bean6.GoodsBean3.ItemBean4,BaseViewHolder> {
    public Home_Fragment_Item7_Adapter(@Nullable List<Home_Json_Bean.DatasBean.bean6.GoodsBean3.ItemBean4> data) {
        super(R.layout.home_fragment_item1_adapter_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Home_Json_Bean.DatasBean.bean6.GoodsBean3.ItemBean4 item) {
        helper.setText(R.id.home_fragment_item1_adapter_shopitem_name,item.getGoods_name());
        helper.setText(R.id.home_fragment_item1_adapter_shopitem_price,item.getGoods_promotion_price());
        GlideApp.with(mContext).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(item.getGoods_image()).skipMemoryCache(true).into((ImageView) helper.getView(R.id.home_fragment_item1_adapter_shopitem_img));
        View view=helper.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopingDetailsActivity.runActivity(mContext,item.getGoods_id());
            }
        });
    }
}
