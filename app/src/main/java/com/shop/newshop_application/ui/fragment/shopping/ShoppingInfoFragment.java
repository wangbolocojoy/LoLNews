package com.shop.newshop_application.ui.fragment.shopping;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
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
import com.shop.newshop_application.http.result.shop.ShopDetail_JesonBean;
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
    public final static String JSON = "json";
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
    private ShopDetail_JesonBean jesonBean;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (ShopingDetailsActivity) context;
        activity.setHandler(mHandler);
    }
    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("收到消息");
                Bundle bundle=msg.getData();
                jesonBean= (ShopDetail_JesonBean) bundle.getSerializable(JSON);
                addData();
                    break;
                    default:
                        break;
            }
        }
    };
    @Override
    protected void setContent() {
        setContentView(R.layout.fragment_shopping_info);
    }

    @Override
    protected boolean initData() {
        BannerImgList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        tabTextList = new ArrayList<>();



        return true;
    }


    @Override
    protected void initView() {
        Log.d("商品f");
        setTitleVisible(false);
        tabTextList.add(tvGoodsDetail);
        tabTextList.add(tvGoodsConfig);
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

    }

    private void addData() {
        for (int i = 0; i <jesonBean.getDatas().getImage_list().size() ; i++) {
                BannerImgList.add(jesonBean.getDatas().getImage_list().get(i).get_mid());
        }
        Log.d("收到"+BannerImgList.size()+"张图片");
        vpItemGoodsImg.setImages(BannerImgList);

        vpItemGoodsImg.start();

//        BannerImgList.add("http://www.chawo.com/data/upload/shop/store/goods/2/2017/12/2_05659545651004846_360.jpg");
//        BannerImgList.add("http://www.chawo.com/data/upload/shop/store/goods/2/2017/12/2_05659546500503824_360.jpg");
//        BannerImgList.add("http://www.chawo.com/data/upload/shop/store/goods/2/2017/12/2_05659546516346281_360.jpg");
//        BannerImgList.add("http://www.chawo.com/data/upload/shop/store/goods/2/2017/12/2_05659546533300064_360.jpg");
//        BannerImgList.add("http://www.chawo.com/data/upload/shop/store/goods/2/2017/12/2_05659546553217144_360.jpg");
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
            case R.id.fab_up_slide:
                //点击滑动到顶部
                svGoodsInfo.smoothScrollTo(0, 0);
                svSwitch.smoothClose(true);
                break;
            case R.id.ll_goods_detail:
                //商品详情tab
                nowIndex = 0;
                scrollCursor();
                switchFragment(nowFragment, infoWebFragment);
                nowFragment = infoWebFragment;
                break;
            case R.id.ll_goods_config:
                //规格参数tab
                nowIndex = 1;
                scrollCursor();
                switchFragment(nowFragment, infoConfigFragment);
                nowFragment = infoConfigFragment;
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
            tabTextList.get(i).setTextColor(i == nowIndex ? R.color.theme_color :R.color.black);
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
