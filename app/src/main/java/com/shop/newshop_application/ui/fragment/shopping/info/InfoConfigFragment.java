package com.shop.newshop_application.ui.fragment.shopping.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rhino.ui.utils.Log;
import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.shop.GoodsConfigAdapter;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.http.result.test.GoodsConfigBean;
import com.shop.newshop_application.views.item.ItemListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by a on 2017/12/29.
 */

public class InfoConfigFragment extends BaseHttpFragment {
    @BindView(R.id.infoconfig_listview)
    ItemListView infoconfigListview;
    private List<GoodsConfigBean> data;

    @Override
    protected void setContent() {
        setContentView(R.layout.fragment_infoconfig);

    }

    @Override
    protected boolean initData() {
        data = new ArrayList<>();
        data.add(new GoodsConfigBean("品牌", "Letv/乐视"));
        data.add(new GoodsConfigBean("型号", "LETV体感-超级枪王"));

        return true;

    }

    @Override
    protected void initView() {
        Log.d("规格参数");
        setTitleVisible(false);
        infoconfigListview.setFocusable(false);
        infoconfigListview.setAdapter(new GoodsConfigAdapter(getActivity(), data));


    }


}
