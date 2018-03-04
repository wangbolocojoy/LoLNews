package com.blog.news.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.rhino.ui.widget.CircleImageView;
import com.blog.news.R;
import com.blog.news.base.BaseHttpFragment;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author a
 * @date 2018/1/2
 */

public class TabShopMineFragment extends BaseHttpFragment {
    @BindView(R.id.tab_shop_home_userimg)
    CircleImageView tabShopHomeUserimg;
    @BindView(R.id.tab_shop_home_userorder)
    LinearLayout tabShopHomeUserorder;
    @BindView(R.id.tab_shop_home_allorder)
    LinearLayout tabShopHomeAllorder;
    @BindView(R.id.tab_shop_home_substitute_pay)
    LinearLayout tabShopHomeSubstitutePay;
    @BindView(R.id.tab_shop_home_collect_goods)
    LinearLayout tabShopHomeCollectGoods;
    @BindView(R.id.tab_shop_home_pending_goods)
    LinearLayout tabShopHomePendingGoods;
    @BindView(R.id.tab_shop_home_pending_evaluate)
    LinearLayout tabShopHomePendingEvaluate;
    @BindView(R.id.tab_shop_home_pending_refund)
    LinearLayout tabShopHomePendingRefund;
    @BindView(R.id.tab_shop_home_receiving_address)
    LinearLayout tabShopHomeReceivingAddress;
    @BindView(R.id.tab_shop_home_user_setting)
    LinearLayout tabShopHomeUserSetting;
    Unbinder unbinder;

    @Override
    protected void setContent() {
        setContentView(R.layout.tab_shop_mine_fragment);

    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        setTitleBackKeyVisible(false);
        setTitle("我的");


    }


    @OnClick({R.id.tab_shop_home_userimg, R.id.tab_shop_home_userorder, R.id.tab_shop_home_allorder, R.id.tab_shop_home_substitute_pay, R.id.tab_shop_home_collect_goods, R.id.tab_shop_home_pending_goods, R.id.tab_shop_home_pending_evaluate, R.id.tab_shop_home_pending_refund, R.id.tab_shop_home_receiving_address, R.id.tab_shop_home_user_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_shop_home_userimg:
                break;
            case R.id.tab_shop_home_userorder:
                break;
            case R.id.tab_shop_home_allorder:
                break;
            case R.id.tab_shop_home_substitute_pay:
                break;
            case R.id.tab_shop_home_collect_goods:
                break;
            case R.id.tab_shop_home_pending_goods:
                break;
            case R.id.tab_shop_home_pending_evaluate:
                break;
            case R.id.tab_shop_home_pending_refund:
                break;
            case R.id.tab_shop_home_receiving_address:
                break;
            case R.id.tab_shop_home_user_setting:
                break;
            default:
                break;
        }
    }
}
