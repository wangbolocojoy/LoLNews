package com.blog.news.adapter.blog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blog.news.R;
import com.blog.news.http.result.blog.NewsListInfo;
import com.blog.news.ui.activity.news.ShowNewsActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 *
 * @author MacBookPor
 * @date 2018/2/24
 */

public class FragmentBlogNewsItemListAdapter extends BaseQuickAdapter<NewsListInfo.PostsBean,BaseViewHolder> {

    public FragmentBlogNewsItemListAdapter(@Nullable List<NewsListInfo.PostsBean> data) {
        super(R.layout.liststyle_shop__new_item_home_list_data,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final NewsListInfo.PostsBean item) {
        helper.setText(R.id.tab_shop_new_list_item_titel, item.getTitle());
        helper.setText(R.id.tab_shop_new_list_item_user, item.getAuthor().getNickname());
        helper.getView(R.id.tab_shop_new_list_item_img).setVisibility(View.GONE);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("aa",item);
                ShowNewsActivity.runActivity(mContext, item.getContent(),item.getTitle(),item.getComments());
            }
        });
    }
}
