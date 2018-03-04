package com.blog.news.ui.activity.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blog.news.R;
import com.blog.news.application.MyApplication;
import com.blog.news.base.BaseHttpActivity;
import com.blog.news.http.result.blog.NewsListInfo;
import com.blog.news.utils.AndroidBug5497Workaround;
import com.blog.news.utils.ThreadManager;
import com.blog.news.views.item.FunSwitch;
import com.rhino.ui.utils.Log;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class ShowNewsActivity extends BaseHttpActivity {
    @BindView(R.id.news_webview)
    WebView webView;
    @BindView(R.id.danmuview)
    DanmakuView mDanmakuView;
    @BindView(R.id.check_comment)
    FunSwitch checkComment;
    @BindView(R.id.edtext)
    EditText edtext;
    @BindView(R.id.send_comment)
    Button sendComment;
    private DanmakuContext mDanmakuContext;
    private HashMap<Integer, Integer> maxLinesPair;// 弹幕最大行数
    private HashMap<Integer, Boolean> overlappingEnablePair;// 设置是否重叠
    public static boolean isShow = true;
    private WebSettings webSettings;
    public final static String URL = "url";
    public final static String TITLE = "title";
    public final static String COMMENT = "comment";
    List<NewsListInfo.PostsBean.CommentsBean> commentsBeans;
    public final static String headers = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\"><style>\n" +
            "img { width : 100% }</style> <body><script src=\"https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js\"></script></head>";
    public final static String footer = "</body><html>";
    private int colordata[] = {R.color.hong, R.color.cheng, R.color.huang, R.color.lv, R.color.qing
            , R.color.lan, R.color.zi, R.color.Violet, R.color.red1};
    //    private List<String> comments;
    private String conent;
    public String comments;
    private static BaseDanmakuParser mParser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    @Override
    protected void setContent() {
        setContentView(R.layout.activity_show_news);
        AndroidBug5497Workaround.assistActivity(this);
//        Slidr.attach(this);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    @Override
    protected boolean initData() {
        commentsBeans = new ArrayList<>();
         conent = getIntent().getStringExtra(URL);
        return true;
    }

    /**
     * init the view
     */
    @Override
    protected void initView() {
        inButterKnifeView();
        setTitle(getIntent().getStringExtra(TITLE));
        mDanmakuContext = DanmakuContext.create();
        initDanmaku();
//        webView.loadUrl(getIntent().getStringExtra(URL));
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });
        initwebinfo();
    }

    public void initwebinfo(){
        commentsBeans = (List<NewsListInfo.PostsBean.CommentsBean>) getIntent().getSerializableExtra(COMMENT);

       ThreadManager.getManager().post(ThreadManager.THREAD_BACKGROUND,new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {

                commentHtml(commentsBeans);
                for (int i = 0; i < commentsBeans.size(); i++) {
                    addDanmaku(false, commentsBeans.get(i).getContent());
                }
                Log.d("111111");
                comments = "<p ><h3>评论：</h3></p><hr  id = \"commentAnchor\">" + commentHtml(commentsBeans);
                Log.d("222222");
                try {
                    Thread.sleep(2222);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                {

                }
            }

        });
       Log.d("33333");
        webView.loadDataWithBaseURL(null, headers + conent + comments + footer, "text/html", "UTF-8", null);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void runActivity(Context context, String url, String title, List<NewsListInfo.PostsBean.CommentsBean> commentsBeans) {
        Intent intent = new Intent(context, ShowNewsActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        intent.putExtra(COMMENT, (Serializable) commentsBeans);
        context.startActivity(intent);
    }

    public String commentHtml(List<NewsListInfo.PostsBean.CommentsBean> commentsBeans) {
        String result = "";
        for (int i = 0; i < commentsBeans.size(); i++) {
            String a = "<p> <h6>" + (commentsBeans.get(i).getName()) + "</h6> <h5>" + (commentsBeans.get(i).getContent()) + "</h5><hr size=1></p>";
            result = result + a;
        }
        return result;
    }

    private void initDanmaku() {

        // 设置最大行数,从右向左滚动(有其它方向可选)
        maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5);
        // 设置是否禁止重叠
        overlappingEnablePair = new HashMap<>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_LR, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_BOTTOM, true);
        mDanmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3) //设置描边样式
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f) //是否启用合并重复弹幕
                .setScaleTextSize(1.2f) //设置弹幕滚动速度系数,只对滚动弹幕有效
//                .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer  设置缓存绘制填充器，
                // 默认使用{@link SimpleTextCacheStuffer}只支持纯文字显示,
                // 如果需要图文混排请设置{@link SpannedCacheStuffer}
                // 如果需要定制其他样式请扩展{@link SimpleTextCacheStuffer}|{@link SpannedCacheStuffer}
                .setMaximumLines(maxLinesPair) //设置最大显示行数
                .preventOverlapping(overlappingEnablePair); //设置防弹幕重叠，null为允许重叠

        if (mDanmakuView != null) {
            mDanmakuView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    mDanmakuView.start();

                }

                @Override
                public void updateTimer(DanmakuTimer timer) {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {

                }

                @Override
                public void drawingFinished() {

                }
            });
        }
        mDanmakuView.prepare(mParser, mDanmakuContext);
        mDanmakuView.showFPS(true); //是否显示FPS
        mDanmakuView.enableDanmakuDrawingCache(true);
    }
    private void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    /**
     * 添加文本弹幕
     *
     * @param islive  //是否是直播弹幕
     * @param comment //弹幕
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addDanmaku(boolean islive, String comment) {

        BaseDanmaku danmaku = mDanmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || mDanmakuView == null) {
            Log.d("danmaku" + danmaku+"mDanmakuView"+mDanmakuView);
//            Log.d("mDanmakuView"+mDanmakuView);
            return;
        }
        Log.d("弹幕conent" + comment);
        danmaku.text = comment;
        danmaku.padding = 5;
//        danmaku.priority = 0;  //0 表示可能会被各种过滤器过滤并隐藏显示 //1 表示一定会显示, 一般用于本机发送的弹幕
//        danmaku.isLive = islive;
//        danmaku.timeOffset = mDanmakuView.getCurrentTime() + 2000; //显示时间
        danmaku.textSize = sp2px((int)(Math.random()*(40-20+1)+20));
        danmaku.textColor = getColor(colordata[new Random().nextInt(colordata.length)]);
        danmaku.textShadowColor = Color.WHITE; //阴影/描边颜色
        danmaku.borderColor = 0; //边框颜色，0表示无边框
        mDanmakuView.addDanmaku(danmaku);

    }
    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }
    @SuppressLint("NewApi")
    @OnClick({R.id.send_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_comment:
                if (!TextUtils.isEmpty(edtext.getText())){
                    addDanmaku(false,edtext.getText().toString());
                    edtext.setText("");

                }else {
                    showAlert("不能发送空的弹幕");
                }
                break;
            default:
                break;
        }
    }
}


