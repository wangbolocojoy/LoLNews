package com.blog.news.ui.fragment.notused;

import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhino.ui.utils.Log;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.blog.news.R;
import com.blog.news.adapter.tabhomeitem.Bean10ViewBinder;
import com.blog.news.adapter.tabhomeitem.Bean1ViewBinder;
import com.blog.news.adapter.tabhomeitem.Bean2ViewBinder;
import com.blog.news.adapter.tabhomeitem.Bean4ViewBinder;
import com.blog.news.adapter.tabhomeitem.Bean5ViewBinder;
import com.blog.news.adapter.tabhomeitem.Bean6ViewBinder;
import com.blog.news.adapter.tabhomeitem.Bean7ViewBinder;
import com.blog.news.adapter.tabhomeitem.Bean8ViewBinder;
import com.blog.news.adapter.tabhomeitem.Bean9ViewBinder;
import com.blog.news.base.BaseHttpFragment;
import com.blog.news.constant.UrlConstant;
import com.blog.news.http.result.home.Home_Json_Bean;
import com.blog.news.ui.activity.SerchShopPingActivity;
import com.blog.news.utils.helper.GsonUtil;
import com.blog.news.views.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Unir|Superman
 * on 2017/11/23 11:09
 * on Administrator
 * on NewShop_Application
 *
 * @author MacBookPor
 */

public class TabHomeFragment extends BaseHttpFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    List<Home_Json_Bean.DatasBean.NavBean> list;
    @BindView(R.id.incloud_search_tab_img)
    ImageView incloudSearchTabImg;
    @BindView(R.id.incloud_search_tab_searchrows)
    ClearEditText incloudSearchTabSearchrows;
    @BindView(R.id.incloud_search_tab_serchview)
    CardView incloudSearchTabSerchview;
    @BindView(R.id.incloud_search_tab_searchrows_tab)
    TextView incloudSearchTabMore;
    private MultiTypeAdapter adapter;
    /* Items 等价于 ArrayList<Object> */
    private Items items;
    @Override
    protected void setContent() {
        setContentView(R.layout.tab_home_fragment);
    }

    @Override
    protected boolean initData() {
        items = new Items();
        adapter = new MultiTypeAdapter(items);
        list = new ArrayList<>();
        return true;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(manager);
        adapter.register(Home_Json_Bean.DatasBean.bean1.class, new Bean1ViewBinder());
        adapter.register(Home_Json_Bean.DatasBean.class, new Bean2ViewBinder());
//        adapter.register(Home_Json_Bean.DatasBean.bean2.Home1Bean.class, new Bean3ViewBinder());
        adapter.register(Home_Json_Bean.DatasBean.bean3.GoodsBean.class, new Bean4ViewBinder());
        adapter.register(Home_Json_Bean.DatasBean.bean4.GoodsBean1.class, new Bean5ViewBinder());
        adapter.register(Home_Json_Bean.DatasBean.bean5.GoodsBean2.class, new Bean6ViewBinder());
        adapter.register(Home_Json_Bean.DatasBean.bean6.GoodsBean3.class, new Bean7ViewBinder());
        adapter.register(Home_Json_Bean.DatasBean.bean7.GoodsBean4.class, new Bean8ViewBinder());
        adapter.register(Home_Json_Bean.DatasBean.bean8.GoodsBean5.class, new Bean9ViewBinder());
        adapter.register(Home_Json_Bean.DatasBean.bean9.GoodsBean6.class, new Bean10ViewBinder());
        recyclerview.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                items.clear();
                refreshlayout.finishRefresh(2000);
                getallhomeinfo();
            }
        });
        refreshLayout.autoRefresh();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void getallhomeinfo() {
        doPost(UrlConstant.CHECKALLSHOPPINGLISTCHAWO);

    }

    @Override
    protected void CallbackHandler(int event, String url, String data, Object reqData) {
        super.CallbackHandler(event, url, data, reqData);
        switch (url) {
            case UrlConstant.CHECKALLSHOPPINGLISTCHAWO:
                CheckHomeInfo(event, data);
                break;
            default:
                break;
        }
    }

    private void CheckHomeInfo(int event, String data) {
        if (!isHttpRequestSuccess(event)) {
            showAlert("请求失败");
            Log.d("data" + data);
        } else {
            Log.d("data" + data);
            Home_Json_Bean json_bean = GsonUtil.GsonToBean(data, Home_Json_Bean.class);
            if (json_bean.getDatas() != null) {
                items.add(json_bean.getDatas().getBeandata1());
                items.add(json_bean.getDatas());
//                items.add(json_bean.getDatas().getBeandata2().getHome1());
                items.add(json_bean.getDatas().getBeandata3().getGoods());
                items.add(json_bean.getDatas().getBeandata4().getGoods());
                items.add(json_bean.getDatas().getBeandata5().getGoods());
                items.add(json_bean.getDatas().getBeandata6().getGoods());
                items.add(json_bean.getDatas().getBeandata7().getGoods());
                items.add(json_bean.getDatas().getBeandata8().getGoods());
                items.add(json_bean.getDatas().getBeandata9().getGoods());

            }
            adapter.setItems(items);
            adapter.notifyDataSetChanged();

        }
    }



    @OnClick({R.id.incloud_search_tab_img, R.id.incloud_search_tab_searchrows, R.id.incloud_search_tab_serchview, R.id.incloud_search_tab_searchrows_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.incloud_search_tab_img:
                showAlert("扫一扫");
                break;
            case R.id.incloud_search_tab_searchrows:
                break;
            case R.id.incloud_search_tab_serchview:
                break;
            case R.id.incloud_search_tab_searchrows_tab:
                String a=incloudSearchTabSearchrows.getText().toString();
                Log.d(a);
                SerchShopPingActivity.runActivity(getContext(),incloudSearchTabSearchrows.getText().toString());
                break;
            default:
                break;
        }
    }
}