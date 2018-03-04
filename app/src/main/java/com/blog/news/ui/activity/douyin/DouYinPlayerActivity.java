package com.blog.news.ui.activity.douyin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

import com.blog.news.R;
import com.blog.news.base.BaseHttpActivity;

import butterknife.BindView;

public class DouYinPlayerActivity extends BaseHttpActivity {

    public final static String TITLE = "playerurl";
    @BindView(R.id.videoView)
    VideoView videoView;
    private String url;
    @Override
    protected void setContent() {
        setContentView(R.layout.activity_dou_yin_player);
    }

    @Override
    protected boolean initData() {
        url=mExtras.getString(TITLE);
        return true;
    }

    @Override
    protected void initView() {
        inButterKnifeView();
        videoView.setMediaController(new MediaController(this));

        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
    }
    public static void runActivity(Context context, String ShopId) {
        Intent intent = new Intent(context, DouYinPlayerActivity.class);
        intent.putExtra(TITLE, ShopId);
        context.startActivity(intent);
    }


}
