package com.blog.news.adapter.tabshoppingcar;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.blog.news.http.result.home.Home_Json_Bean;

import java.util.List;

/**
 * Created by MacBookPor on 2018/1/16.
 */

public class ShoppingCar_ListAdapter extends BaseQuickAdapter<Home_Json_Bean.DatasBean.bean3.GoodsBean.ItemBean1,BaseViewHolder>{
    public ShoppingCar_ListAdapter(@Nullable List<Home_Json_Bean.DatasBean.bean3.GoodsBean.ItemBean1> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Home_Json_Bean.DatasBean.bean3.GoodsBean.ItemBean1 item) {

    }

}
