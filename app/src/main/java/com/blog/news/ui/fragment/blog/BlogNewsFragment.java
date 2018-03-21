package com.blog.news.ui.fragment.blog;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blog.news.R;
import com.blog.news.adapter.blog.FragmentBlogNewsItemListAdapter;
import com.blog.news.base.LazyLoadFragment;
import com.blog.news.constant.UrlConstant;
import com.blog.news.http.request.blog.NewsRequest;
import com.blog.news.http.result.blog.NewsListInfo;
import com.blog.news.utils.helper.JsonHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rhino.ui.utils.Log;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 *
 * @author MacBookPor
 * @date 2018/2/23
 */

@SuppressLint("ValidFragment")
public class BlogNewsFragment extends LazyLoadFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    FragmentBlogNewsItemListAdapter blogNewsItemListAdapter;
    private int newsid = 2;

    @Override
    protected void setContent() {
        setContentView(R.layout.blog_newstype_list_fragment);
    }

    @Override
    protected boolean initData() {


        return true;
    }

    public BlogNewsFragment(int id){
        this.newsid=id;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
        GridLayoutManager gridlayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridlayoutManager);
        blogNewsItemListAdapter = new FragmentBlogNewsItemListAdapter(null);
        blogNewsItemListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerview.setAdapter(blogNewsItemListAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh();
                getnewsInfo(newsid);
            }
        });
        refreshLayout.autoRefresh();
    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void getInfo() {

    }
    public void getnewsInfo(int a) {
        if (checkNetWork()) {
            doPost(UrlConstant.NEWS_TITELLIST,new NewsRequest(a));
        }
    }

    @Override
    protected void CallbackHandler(int event, String url, String data, Object reqData) {
        super.CallbackHandler(event, url, data, reqData);
        switch (url) {
            case UrlConstant.NEWS_TITELLIST:
                getNewsListInfo(event,data);
                break;
            default:
                break;
        }
    }

    private void getNewsListInfo(int event, String data) {
        if (!isHttpRequestSuccess(event)){
            Log.d("获取新闻列表失败");
        }else {
            Log.d("获取新闻列表成功");
            NewsListInfo info = JsonHelper.formatObj(data,NewsListInfo.class);
            if (info.getPosts().size()!=0){
                    blogNewsItemListAdapter.setNewData(info.getPosts());
            }
        }


    }


}
