package com.shop.newshop_application.ui.fragment.shopping;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rhino.ui.utils.Log;
import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.ui.fragment.shopping.info.InfoConfigFragment;
import com.shop.newshop_application.ui.fragment.shopping.info.InfoWebFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author a
 * @date 2017/12/28
 */

public class ShoppingDetailsFragment extends BaseHttpFragment {
    @BindView(R.id.tv_goods_detail)
    TextView tvGoodsDetail;
    @BindView(R.id.ll_goods_detail)
    LinearLayout llGoodsDetail;
    @BindView(R.id.tv_goods_config)
    TextView tvGoodsConfig;
    @BindView(R.id.ll_goods_config)
    LinearLayout llGoodsConfig;
    @BindView(R.id.v_tab_cursor)
    View vTabCursor;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private int nowIndex;
    private float fromX;
    private List<TextView> tabTextList;
    private InfoConfigFragment infoConfigFragment;
    private InfoWebFragment infoWebFragment;
    private Fragment nowFragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void setContent() {
        setContentView(R.layout.fragment_shopping_details);
    }

    @Override
    protected boolean initData() {
        infoConfigFragment=new InfoConfigFragment();
        infoWebFragment=new InfoWebFragment();
        tabTextList=new ArrayList<>();

        return true;
    }

    @Override
    protected void initView() {
        Log.d("详情页");
        setTitleVisible(false);
        setTitleBackKeyVisible(false);
        tabTextList.add(tvGoodsDetail);
        tabTextList.add(tvGoodsConfig);
        nowFragment=infoWebFragment;
        fragmentManager=getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_content,nowFragment).commitAllowingStateLoss();

    }

    @OnClick({R.id.ll_goods_detail, R.id.ll_goods_config})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_goods_detail:
                //商品详情tab
                switchFragment(nowFragment, infoWebFragment);
                nowIndex = 0;
                nowFragment = infoWebFragment;
                scrollCursor();
                break;
            case R.id.ll_goods_config:
                //规格参数tab
                switchFragment(nowFragment, infoConfigFragment);
                nowIndex = 1;
                nowFragment = infoConfigFragment;
                scrollCursor();
                break;
            default:
                break;
        }
    }
    /**
     * 滑动游标
     */
    @SuppressLint("ResourceAsColor")
    private void scrollCursor() {
        TranslateAnimation anim = new TranslateAnimation(fromX, nowIndex * vTabCursor.getWidth(), 0, 0);
        anim.setFillAfter(true);//设置动画结束时停在动画结束的位置
        anim.setDuration(50);
        //保存动画结束时游标的位置,作为下次滑动的起点
        fromX = nowIndex * vTabCursor.getWidth();
        vTabCursor.startAnimation(anim);

        //设置Tab切换颜色
        for (int i = 0; i < tabTextList.size(); i++) {
            tabTextList.get(i).setTextColor(i == nowIndex ? R.color.theme_color: R.color.black_10);
        }
    }

    /**
     * 切换Fragment
     * <p>(hide、show、add)
     * @param fromFragment
     * @param toFragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (nowFragment != toFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {    // 先判断是否被add过
                fragmentTransaction.hide(fromFragment).add(R.id.fl_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
