package com.blog.news.adapter.shop;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.blog.news.R;
import com.blog.news.http.result.taoutiaoresult.TouTiaoListInfo.DataBean;
import com.blog.news.ui.activity.news.ShowNewsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unir|Superman
 * on 2017/12/4 17:08.
 * on Administrator
 * on NewShop_Application
 */

public class FragmentNewsListAdapter extends BaseQuickAdapter<DataBean,BaseViewHolder>{
    /**
     * @param data
     */
    public FragmentNewsListAdapter(@Nullable List<DataBean> data) {
        super(R.layout.liststyle_item_new_list_data_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final DataBean item) {
        helper.setText(R.id.new_titel,item.getTitle());
        View view=helper.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
//                ShowNewsActivity.runActivity(mContext,item.getTitle(),item.getUrl());
            }
        });

        NineGridView nineGridView=helper.getView(R.id.new_img);
        ArrayList<ImageInfo> infos=new ArrayList<>();
        if (item.getImageUrls()!=null&&item.getImageUrls().size()>0){
            for (String image:item.getImageUrls()) {
                ImageInfo imageInfo=new ImageInfo();
                imageInfo.setBigImageUrl(image);
                imageInfo.setThumbnailUrl(image);
                infos.add(imageInfo);
            }
        }
        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext,infos));
    }
}
