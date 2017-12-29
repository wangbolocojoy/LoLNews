package com.shop.newshop_application.ui.fragment.shopping;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rhino.ui.utils.Log;
import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.glide.GlideApp;
import com.shop.newshop_application.ui.activity.shoppingshow.ShopingDetailsActivity;
import com.shop.newshop_application.ui.fragment.shopping.info.InfoConfigFragment;
import com.shop.newshop_application.ui.fragment.shopping.info.InfoWebFragment;
import com.shop.newshop_application.views.SlideDetailsLayout;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by a on 2017/12/28.
 */

public class ShoppingInfoFragment extends BaseHttpFragment implements SlideDetailsLayout.OnSlideDetailsListener{

    List<String> BannerImgList;
    Unbinder unbinder;
    @BindView(R.id.vp_item_goods_img)
    Banner vpItemGoodsImg;
    @BindView(R.id.tv_goods_title)
    TextView tvGoodsTitle;
    @BindView(R.id.tv_new_price)
    TextView tvNewPrice;
    @BindView(R.id.tv_old_price)
    TextView tvOldPrice;
    @BindView(R.id.ll_activity)
    LinearLayout llActivity;
    @BindView(R.id.tv_current_goods)
    TextView tvCurrentGoods;
    @BindView(R.id.ll_current_goods)
    LinearLayout llCurrentGoods;
    @BindView(R.id.iv_ensure)
    ImageView ivEnsure;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @BindView(R.id.tv_good_comment)
    TextView tvGoodComment;
    @BindView(R.id.iv_comment_right)
    ImageView ivCommentRight;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.ll_empty_comment)
    LinearLayout llEmptyComment;
    @BindView(R.id.vp_recommend)
    Banner vpRecommend;
    @BindView(R.id.ll_recommend)
    LinearLayout llRecommend;
    @BindView(R.id.ll_pull_up)
    LinearLayout llPullUp;
    @BindView(R.id.sv_goods_info)
    ScrollView svGoodsInfo;
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
    @BindView(R.id.sv_switch)
    SlideDetailsLayout svSwitch;
    @BindView(R.id.fab_up_slide)
    FloatingActionButton fabUpSlide;
    /** 当前商品详情数据页的索引分别是图文详情、规格参数 */
    private int nowIndex;
    private float fromX;
    private InfoConfigFragment infoConfigFragment;
    private InfoWebFragment infoWebFragment;
    private Fragment nowFragment;
    private List<TextView> tabTextList;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private ShopingDetailsActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (ShopingDetailsActivity) context;

    }

    @Override
    protected void setContent() {
        setContentView(R.layout.fragment_shopping_info);
    }

    @Override
    protected boolean initData() {
        BannerImgList = new ArrayList<>();
        addData();
        fragmentList = new ArrayList<>();
        tabTextList = new ArrayList<>();
        tabTextList.add(tvGoodsDetail);
        tabTextList.add(tvGoodsConfig);
        return true;
    }


    @Override
    protected void initView() {
        Log.d("商品f");
        setTitleVisible(false);
//        setDetailData();
        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        fabUpSlide.hide();

        vpItemGoodsImg.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GlideApp.with(getContext()).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(path).skipMemoryCache(true).into(imageView);
            }
        });
        vpItemGoodsImg.setImages(BannerImgList);

        vpItemGoodsImg.setBannerAnimation(Transformer.RotateDown);
        vpItemGoodsImg.start();

    }


    private void addData() {
        BannerImgList.add("http://img4.hqbcdn.com/product/79/f3/79f3ef1b0b2283def1f01e12f21606d4.jpg");
        BannerImgList.add("http://img14.hqbcdn.com/product/77/6c/776c63e6098f05fdc5639adc96d8d6ea.jpg");
        BannerImgList.add("http://img13.hqbcdn.com/product/41/ca/41cad5139371e4eb1ce095e5f6224f4d.jpg");
        BannerImgList.add("http://img10.hqbcdn.com/product/fa/ab/faab98caca326949b87b770c8080e6cf.jpg");
        BannerImgList.add("http://img2.hqbcdn.com/product/6b/b8/6bb86086397a8cd0525c449f29abfaff.jpg");
        //初始化商品图片轮播
    }

    /**
     * 加载完商品详情执行
     */
    public void setDetailData() {
        infoConfigFragment = new InfoConfigFragment();
        infoWebFragment = new InfoWebFragment();
        fragmentList.add(infoConfigFragment);
        fragmentList.add(infoWebFragment);

        nowFragment = infoWebFragment;
        fragmentManager = getChildFragmentManager();
        //默认显示商品详情tab
        fragmentManager.beginTransaction().replace(R.id.fl_content, nowFragment).commitAllowingStateLoss();
    }
    @Override
    public void onStatucChanged(SlideDetailsLayout.Status status) {
        if (status == SlideDetailsLayout.Status.OPEN) {
            //当前为图文详情页
            fabUpSlide.show();
            activity.shopDetailsVpager.setNoScroll(true);
            activity.tvTitle.setVisibility(View.VISIBLE);
            activity.pstsTabs.setVisibility(View.GONE);
        } else {
            //当前为商品详情页
            fabUpSlide.hide();
            activity.shopDetailsVpager.setNoScroll(false);
            activity.tvTitle.setVisibility(View.GONE);
            activity.pstsTabs.setVisibility(View.VISIBLE);
        }
    }







    @OnClick({R.id.ll_pull_up, R.id.ll_goods_detail, R.id.ll_goods_config, R.id.fab_up_slide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_pull_up:
                //上拉查看图文详情
                svSwitch.smoothOpen(true);
                break;
            case R.id.ll_goods_detail:
                //点击滑动到顶部
                svGoodsInfo.smoothScrollTo(0, 0);
                svSwitch.smoothClose(true);
                break;
            case R.id.ll_goods_config:
                //商品详情tab
                nowIndex = 0;
                scrollCursor();
                switchFragment(nowFragment, infoWebFragment);
                nowFragment = infoWebFragment;
                break;
            case R.id.fab_up_slide:
                break;
            default:
                break;
        }
    }
    /**
     * 滑动游标
     */
    private void scrollCursor() {
        TranslateAnimation anim = new TranslateAnimation(fromX, nowIndex * vTabCursor.getWidth(), 0, 0);
        anim.setFillAfter(true);//设置动画结束时停在动画结束的位置
        anim.setDuration(50);
        //保存动画结束时游标的位置,作为下次滑动的起点
        fromX = nowIndex * vTabCursor.getWidth();
        vTabCursor.startAnimation(anim);

        //设置Tab切换颜色
        for (int i = 0; i < tabTextList.size(); i++) {
            tabTextList.get(i).setTextColor(i == nowIndex ? getResources().getColor(R.color.red) : getResources().getColor(R.color.black_30));
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

            if (!toFragment.isAdded()) {
                // 先判断是否被add过
                fragmentTransaction.hide(fromFragment).add(R.id.fl_content, toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到activity中
            } else {
                fragmentTransaction.hide(fromFragment).show(toFragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
