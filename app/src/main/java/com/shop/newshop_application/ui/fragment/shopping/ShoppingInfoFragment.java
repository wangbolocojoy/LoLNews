package com.shop.newshop_application.ui.fragment.shopping;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.glide.GlideApp;
import com.shop.newshop_application.views.SlideDetailsLayout;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by a on 2017/12/28.
 */

public class ShoppingInfoFragment extends BaseHttpFragment {

    List<String> BannerImgList;
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



    @Override
    protected void setContent() {
        setContentView(R.layout.fragment_shopping_info);
    }

    @Override
    protected boolean initData() {
        BannerImgList = new ArrayList<>();
        addData();

        return true;
    }

    private void addData() {
        BannerImgList.add("http://img4.hqbcdn.com/product/79/f3/79f3ef1b0b2283def1f01e12f21606d4.jpg");
        BannerImgList.add("http://img14.hqbcdn.com/product/77/6c/776c63e6098f05fdc5639adc96d8d6ea.jpg");
        BannerImgList.add("http://img13.hqbcdn.com/product/41/ca/41cad5139371e4eb1ce095e5f6224f4d.jpg");
        BannerImgList.add("http://img10.hqbcdn.com/product/fa/ab/faab98caca326949b87b770c8080e6cf.jpg");
        BannerImgList.add("http://img2.hqbcdn.com/product/6b/b8/6bb86086397a8cd0525c449f29abfaff.jpg");
        //初始化商品图片轮播
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
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



}
