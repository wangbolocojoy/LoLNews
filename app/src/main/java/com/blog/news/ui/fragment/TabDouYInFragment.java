package com.blog.news.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rhino.ui.utils.Log;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.blog.news.R;
import com.blog.news.adapter.douyin.FragmentDouYinListAdapter;
import com.blog.news.base.BaseHttpFragment;
import com.blog.news.constant.UrlConstant;
import com.blog.news.http.result.douyin.DouYinJsonBean;
import com.blog.news.utils.helper.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author MacBookPor
 * @date 2018/1/30
 */

public class TabDouYInFragment extends BaseHttpFragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    FragmentDouYinListAdapter fragmentDouYinListAdapter;
    List<DouYinJsonBean.AwemeListBean> asd;
    @Override
    protected void setContent() {
        setContentView(R.layout.tab_dou_yin_fragment);

    }

    @Override
    protected boolean initData() {
        asd=new ArrayList<>();
        return true;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
        GridLayoutManager manager= new GridLayoutManager(getActivity(),2);
        fragmentDouYinListAdapter =new FragmentDouYinListAdapter(null);
        fragmentDouYinListAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(fragmentDouYinListAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                getalldouyininfo();
            }
        });
        refreshLayout.autoRefresh();

    }
    public void getalldouyininfo(){
        if (!checkNetWork()){
            doPost(UrlConstant.ChCEK_DOUYIN);
        }else {
            showAlert("请检查网络连接");
        }

    }

    @Override
    protected void CallbackHandler(int event, String url, String data, Object reqData) {
        super.CallbackHandler(event, url, data, reqData);
        switch (url){
            case UrlConstant.ChCEK_DOUYIN:
                checkdouyininfo(event,data);
                break;
                default:
                    break;
        }
    }

    private void checkdouyininfo(int event, String data) {
        if (isHttpRequestSuccess(event)){
            Log.d("successful"+data);
            DouYinJsonBean jsonBean= GsonUtil.GsonToBean(data,DouYinJsonBean.class);
            asd.addAll(jsonBean.getAweme_list());
            Log.d("asd"+asd.size() +"asdtostring___"+asd.get(0).getVideo().getCover().getUrl_list().get(0));
            fragmentDouYinListAdapter.setNewData(jsonBean.getAweme_list());
            fragmentDouYinListAdapter.notifyDataSetChanged();


        }else {

        }
    }
}
