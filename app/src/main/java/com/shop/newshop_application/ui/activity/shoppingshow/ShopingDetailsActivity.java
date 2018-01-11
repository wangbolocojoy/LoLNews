package com.shop.newshop_application.ui.activity.shoppingshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxz.PagerSlidingTabStrip;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rhino.ui.utils.Log;
import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.shop.ItemTitlePagerAdapter;
import com.shop.newshop_application.base.BaseHttpActivity;
import com.shop.newshop_application.constant.UrlConstant;
import com.shop.newshop_application.http.request.shop.ShoppingDetail;
import com.shop.newshop_application.http.result.shop.ShopDetail_JesonBean;
import com.shop.newshop_application.ui.activity.MainTabActivicty;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingCommodityFragment;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingDetailsFragment;
import com.shop.newshop_application.ui.fragment.shopping.ShoppingInfoFragment;
import com.shop.newshop_application.utils.helper.GsonUtil;
import com.shop.newshop_application.utils.helper.JsonHelper;
import com.shop.newshop_application.views.NoScrollViewPager;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

public class ShopingDetailsActivity extends BaseHttpActivity {

    public final static String URL = "url";
    public final static String TITLE = "shopid";
    public final static String JSON = "json";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.psts_tabs)
    public
    PagerSlidingTabStrip pstsTabs;
    @BindView(R.id.tv_title)
    public
    TextView tvTitle;
    @BindView(R.id.ll_title_root)
    LinearLayout llTitleRoot;
    @BindView(R.id.shop_details_vpager)
    public
    NoScrollViewPager shopDetailsVpager;
    /**
     * 返回首页
     */
    @BindView(R.id.shop_goto_home)
    LinearLayout llgotohome;
    /**
     * 收藏商品
     */
    @BindView(R.id.shop_goto_collection)
    LinearLayout llgotocollection;
    /**
     * 客服
     */
    @BindView(R.id.shop_goto_Customer)
    LinearLayout llgotocustomer;
    /**
     * 购物车
     */
    @BindView(R.id.shop_goto_shopcar)
    LinearLayout llgotoshopcar;
    /**
     *
     */
    @BindView(R.id.shop_add_shop_to_shopcar)
    LinearLayout lladdshop;

    private List<Fragment> fragmentList = new ArrayList<>();
    private ShoppingCommodityFragment shoppingCommodityFragment;
    private ShoppingDetailsFragment shoppingDetailsFragment;
    private ShoppingInfoFragment shoppingInfoFragment;
    private ItemTitlePagerAdapter itemTitlePagerAdapter;
    private static int ShopSum;
    private String Parmrs;
    private Handler mHandler;
    private ShopDetail_JesonBean jesonBean;
    @Override
    protected void setContent() {
        setContentView(R.layout.activity_shoping_details);
//        Slidr.attach(this);
    }

    @Override
    protected boolean initData() {
        Parmrs = mExtras.getString(TITLE);

        fragmentList = new ArrayList<>();
        fragmentList.add(shoppingInfoFragment = new ShoppingInfoFragment());
        fragmentList.add(shoppingDetailsFragment = new ShoppingDetailsFragment());
        fragmentList.add(shoppingCommodityFragment = new ShoppingCommodityFragment());
        itemTitlePagerAdapter = new ItemTitlePagerAdapter(getSupportFragmentManager(), fragmentList, new String[]{"商品", "详情", "评价"});
        return true;
    }
    @Override
    protected boolean translucentStatusBar() {
        return true;
    }
    @Override
    protected void initView() {
        lladdshop = findSubViewById(R.id.shop_add_shop_to_shopcar);
        inButterKnifeView();
        setTitleVisible(false);
        shopDetailsVpager = findSubViewById(R.id.shop_details_vpager);
        pstsTabs = findSubViewById(R.id.psts_tabs);
        shopDetailsVpager.setAdapter(itemTitlePagerAdapter);
        shopDetailsVpager.setOffscreenPageLimit(3);
        pstsTabs.setViewPager(shopDetailsVpager);
        Log.d("details_Activity");
        Log.d("商品Id" + Parmrs);
        getshoppingdeatilinfo(Parmrs);
    }

    private void getshoppingdeatilinfo(String parmrs) {
        doPost(UrlConstant.CHECKALLSHOPPINGDETAILINFO,new ShoppingDetail(Parmrs));
    }

    public static void runActivity(Context context, String ShopId) {
        Intent intent = new Intent(context, ShopingDetailsActivity.class);
        intent.putExtra(TITLE, ShopId);
        context.startActivity(intent);
    }

    @Override
    protected void CallbackHandler(int event, String url, String data, Object reqData) {
        super.CallbackHandler(event, url, data, reqData);
        switch (url) {
            case UrlConstant.CHECKALLSHOPPINGDETAILINFO:
                checkdetailinfo(event,data);
                break;
            default:
                break;
        }
    }

    /**
     * 获取商品详情
     * @param event
     * @param data
     */
    private void checkdetailinfo(int event, String data) {
        Log.d("请求成功");
        if (!isHttpRequestSuccess(event)){
            showAlert("获取商品详情失败，请稍后再试");
                Log.d("data"+data);
        }else {
            Log.d("data"+data);
            jesonBean=GsonUtil.GsonToBean(data,ShopDetail_JesonBean.class);
            Log.d(jesonBean.toString());
            if (null!=jesonBean.getDatas().getGoods_info()){
                Bundle bundle=new Bundle();
                bundle.putSerializable(JSON,jesonBean);
                Message message=new Message();
                message.setData(bundle);
                message.what=1;
                mHandler.sendMessage(message);
                Log.d(jesonBean.getDatas().getGoods_info().toString());
            }else {
                showAlert("获取商品详情失败，请稍后再试");

            }
        }

    }

    @OnClick({R.id.iv_back, R.id.shop_goto_home, R.id.shop_goto_collection, R.id.shop_goto_Customer, R.id.shop_goto_shopcar, R.id.shop_add_shop_to_shopcar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.shop_goto_home:
                showAlert("gohome");
                finish();
                break;
            case R.id.shop_goto_collection:
                showAlert("go收藏");
                break;
            case R.id.shop_goto_Customer:
                showAlert("go客服");
                break;
            case R.id.shop_goto_shopcar:
                showAlert("goshopcar");
                finish();
                break;
            case R.id.shop_add_shop_to_shopcar:
                showAlert("addshop");
                ShopSum = ShopSum + 1;
                new QBadgeView(this).bindTarget(llgotoshopcar).setBadgeNumber(ShopSum);
                break;

            default:
                break;
        }

    }

    public interface checktab {
        void settab(int a);
    }
    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jesonBean=null;
    }
}
