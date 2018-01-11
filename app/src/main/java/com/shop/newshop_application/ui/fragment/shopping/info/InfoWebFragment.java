package com.shop.newshop_application.ui.fragment.shopping.info;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shop.newshop_application.R;
import com.shop.newshop_application.base.BaseHttpFragment;
import com.shop.newshop_application.views.item.ItemWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
