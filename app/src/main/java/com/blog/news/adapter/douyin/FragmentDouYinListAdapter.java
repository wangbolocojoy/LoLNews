package com.blog.news.adapter.douyin;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.blog.news.R;
import com.blog.news.glide.GlideApp;
import com.blog.news.http.result.douyin.DouYinJsonBean;
import com.blog.news.ui.activity.douyin.DouYinPlayerActivity;

import java.util.List;

/**
 * Created by MacBookPor on 2018/1/30.
 */

public class FragmentDouYinListAdapter extends BaseQuickAdapter<DouYinJsonBean.AwemeListBean,BaseViewHolder>{


    public FragmentDouYinListAdapter( @Nullable List<DouYinJsonBean.AwemeListBean> data) {
        super(R.layout.adapter_douyin_fragment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final DouYinJsonBean.AwemeListBean item) {

        GlideApp.with(mContext).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(item.getVideo().getCover().getUrl_list().get(0)).skipMemoryCache(true).into((ImageView) helper.getView(R.id.douyin_img));
//        ImageView imageView= (ImageView) helper.getConvertView().findViewById(R.id.douyin_play);
        helper.setText(R.id.douyin_autrer,item.getAuthor().getNickname());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DouYinPlayerActivity.runActivity(mContext,item.getVideo().getPlay_addr().getUrl_list().get(3)+item.getVideo().getPlay_addr().getUri());
            }
        });
    }
}
