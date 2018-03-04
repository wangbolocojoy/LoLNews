package com.blog.news.ui.fragment.shopping.info;

import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blog.news.R;
import com.blog.news.base.BaseHttpFragment;
import com.blog.news.views.item.ItemWebView;

import butterknife.BindView;

/**
 * @author a
 * @date 2017/12/29
 */

public class InfoWebFragment extends BaseHttpFragment {
    @BindView(R.id.infoweb_webview)
    ItemWebView infowebWebview;
    /**
     * webview设置
     */
    private WebSettings webSettings;
    /**
     * 商品详情地址URL
     */
    private String ShoppingUrl;
    @Override
    protected void setContent() {
        setContentView(R.layout.fragment_infoweb);
    }

    @Override
    protected boolean initData() {
       ShoppingUrl= mExtra.getString("URL");
        return true;
    }

    @Override
    protected void initView() {
        setTitleVisible(false);
        if (TextUtils.isEmpty(ShoppingUrl)){
            ShoppingUrl="";
        }
        infowebWebview.setFocusable(false);
        infowebWebview.loadUrl(ShoppingUrl);
        webSettings=infowebWebview.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setBlockNetworkImage(true);
        webSettings.setUseWideViewPort(true);
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        infowebWebview.setWebViewClient(new ItemWebViewClient());




    }
    private class ItemWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webSettings.setBlockNetworkImage(false);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }
    }

}
