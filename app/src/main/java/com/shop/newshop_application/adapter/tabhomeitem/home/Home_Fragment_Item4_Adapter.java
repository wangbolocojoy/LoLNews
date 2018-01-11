package com.shop.newshop_application.adapter.tabhomeitem.home;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shop.newshop_application.R;
import com.shop.newshop_application.glide.GlideApp;
import com.shop.newshop_application.http.result.home.Home_Json_Bean;
import com.shop.newshop_application.ui.activity.shoppingshow.ShopingDetailsActivity;

import java.util.List;

/**
 * Created by MacBookPor on 2018/1/6.
 */

public class Home_Fragment_Item4_Adapter extends BaseQuickAdapter<Home_Json_Bean.DatasBean.bean3.GoodsBean.ItemBean1,BaseViewHolder>{
    public Home_Fragment_Item4_Adapter(@Nullable List<Home_Json_Bean.DatasBean.bean3.GoodsBean.ItemBean1> data) {
        super(R.layout.home_fragment_item1_adapter_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Home_Json_Bean.DatasBean.bean3.GoodsBean.ItemBean1 item) {
        GlideApp.with(mContext).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(item.getGoods_image()).skipMemoryCache(true).into((ImageView) helper.getView(R.id.home_fragment_item1_adapter_shopitem_img));
        helper.setText(R.id.home_fragment_item1_adapter_shopitem_name, item.getGoods_name());
        helper.setText(R.id.home_fragment_item1_adapter_shopitem_price, "¥:"+item.getGoods_promotion_price());
        View view=helper.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopingDetailsActivity.runActivity(mContext,item.getGoods_id());
            }
        });
    }
}
