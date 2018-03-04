package com.blog.news.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.blog.news.R;
import com.blog.news.adapter.tabshoppingcar.ShoppingCar_ListAdapter;
import com.blog.news.base.BaseHttpFragment;
import com.blog.news.constant.StringConstant;
import com.blog.news.ui.activity.MainTabActivicty;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author a
 * @date 2017/12/29
 */

public class TabShoppingCarFragment extends BaseHttpFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.incloud_shoppingcar_bottom_scare_all_check)
    CheckBox incloudShoppingcarBottomScareAllCheck;
    @BindView(R.id.incloud_shoppingcar_bottom_scare_total)
    TextView incloudShoppingcarBottomScareTotal;
    @BindView(R.id.incloud_shoppingcar_bottom_settlement)
    TextView incloudShoppingcarBottomSettlement;
    @BindView(R.id.incloud_shoppingcar_bottom_show)
    LinearLayout incloudShoppingcarBottomShow;

    /**
     * 购物车适配器
     */
    private ShoppingCar_ListAdapter adapter;
    private boolean mError = true;
    private boolean mNoData = true;
    private View enpityView;
    private View databullView;
    private  TextView textView;

    /**
     * SetConentView
     */
    @Override
    protected void setContent() {
        setContentView(R.layout.tab_shoppingcar_fragment);

    }

    /**
     * @return 是否初始化数据
     */

    @Override
    protected boolean initData() {

        return true;
    }

    /**
     * 绑定视图
     */
    @Override
    protected void initView() {
        setTitle(StringConstant.SHOPCAR);
        setTitleBackKeyVisible(false);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new ShoppingCar_ListAdapter(null);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
        enpityView = getLayoutInflater().inflate(R.layout.incloud_shopcar_is_null, (ViewGroup) recyclerview.getParent(), false);
        databullView = getLayoutInflater().inflate(R.layout.incloud_shopcar_is_null, (ViewGroup) recyclerview.getParent(), false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000);
                if (mError) {

                    adapter.setEmptyView(enpityView);
                    incloudShoppingcarBottomShow.setVisibility(View.GONE);
                } else {
//                    adapter.setNewData();
                    incloudShoppingcarBottomShow.setVisibility(View.VISIBLE);
                }


            }
        });
        refreshLayout.autoRefresh();
        //随便看看监听
        textView= (TextView) enpityView.findViewById(R.id.tab_shoppingcar_to_home);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainTabActivicty mainTabActivicty= (MainTabActivicty) getActivity();
                mainTabActivicty.setTab(0);
            }
        });
    }

    @OnClick({R.id.incloud_shoppingcar_bottom_scare_all_check, R.id.incloud_shoppingcar_bottom_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.incloud_shoppingcar_bottom_scare_all_check:
                break;
            case R.id.incloud_shoppingcar_bottom_show:
                break;
            default:
                break;
        }
    }
}
