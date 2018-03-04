package com.blog.news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blog.news.R;
import com.blog.news.base.BaseHttpActivity;
import com.blog.news.views.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author MacBookPor
 */
public class SerchShopPingActivity extends BaseHttpActivity {
    public final static String TITLE = "shopid";
    @BindView(R.id.activity_search_tab_black_img)
    ImageView activitySearchTabBlackImg;
    @BindView(R.id.activity_search_tab_imputtext_view)
    ClearEditText activitySearchTabImputtextView;
    @BindView(R.id.activity_search_tab_serchview)
    CardView activitySearchTabSerchview;
    @BindView(R.id.activity_search_tab_tview)
    TextView activitySearchTabTview;
    @BindView(R.id.activity_search_recyleview)
    RecyclerView activitySearchRecyleview;
    private String SerchText;
    @Override
    protected void setContent() {
        setContentView(R.layout.activity_serch_shop_ping);

    }

    @Override
    protected boolean initData() {
        SerchText=mExtras.getString(TITLE);
        return true;
    }

    @Override
    protected void initView() {
        inButterKnifeView();
        setTitleVisible(false);
        activitySearchTabImputtextView.setText(SerchText);


    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    public static void runActivity(Context context, String ShopId) {
        Intent intent = new Intent(context, SerchShopPingActivity.class);
        intent.putExtra(TITLE, ShopId);
        context.startActivity(intent);
    }

    @OnClick({R.id.activity_search_tab_black_img, R.id.activity_search_tab_tview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_search_tab_black_img:

                finish();
                break;
            case R.id.activity_search_tab_tview:
                break;
            default:
                break;
        }
    }
}
