package com.blog.news.adapter.tabhomeitem.home;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rhino.ui.utils.Log;
import com.blog.news.R;
import com.blog.news.http.result.home.Home_Json_Bean;

import java.util.List;

/**
 * Created by Unir|Superman
 * on 2017/12/6 16:57.
 * on Administrator
 * on NewShop_Application
 * @author MacBookPor
 */

public class Home_Fragment_Item2_Adapter extends BaseQuickAdapter<Home_Json_Bean.DatasBean.NavBean,BaseViewHolder> {
    public Home_Fragment_Item2_Adapter(@Nullable List<Home_Json_Bean.DatasBean.NavBean> data) {
        super(R.layout.item_home1_bean, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Home_Json_Bean.DatasBean.NavBean item) {
        helper.setText(R.id.home_dahang_item_naitem_name,item.getName());
        View view=helper.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("选项"+item.getName());
            }
        });
    }
}
