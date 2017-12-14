package com.shop.newshop_application.ui.activity.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.r0adkll.slidr.Slidr;
import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowNewsActivity extends BaseHttpActivity {
    @BindView(R.id.news_webview)
    WebView webView;
    private WebSettings webSettings;
    public final static String URL = "url";
    public final static String TITLE = "title";

    /**
     * set the parent view
     * {@link #setContentView(int)}
     * {@link #setContentView(View)}}
     */
    @Override
    protected void setContent() {
        setContentView(R.layout.activity_show_news);
        Slidr.attach(this);
    }
    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    /**
     * init the data
     *
     * @return true success false failed
     */
    @Override
    protected boolean initData() {
        return true;
    }

    /**
     * init the view
     */
    @Override
    protected void initView() {
        inButterKnifeView();
        setTitle(getIntent().getStringExtra(TITLE));
        webView.loadUrl(getIntent().getStringExtra(URL));
        webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void runActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, ShowNewsActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

}
