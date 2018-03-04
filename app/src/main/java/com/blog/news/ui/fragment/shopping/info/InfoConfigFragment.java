package com.blog.news.ui.fragment.shopping.info;

import com.rhino.ui.utils.Log;
import com.blog.news.R;
import com.blog.news.adapter.shop.GoodsConfigAdapter;
import com.blog.news.base.BaseHttpFragment;
import com.blog.news.http.result.test.GoodsConfigBean;
import com.blog.news.views.item.ItemListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
