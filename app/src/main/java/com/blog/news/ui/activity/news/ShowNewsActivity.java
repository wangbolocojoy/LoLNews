package com.blog.news.ui.activity.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.blog.news.R;
import com.blog.news.base.BaseHttpActivity;
import com.blog.news.constant.UrlConstant;
import com.blog.news.http.request.blog.NewsRequest;
import com.blog.news.http.result.blog.Comment;
import com.blog.news.http.result.blog.NewsInfo;
import com.blog.news.http.result.blog.NewsListInfo;
import com.blog.news.utils.helper.GsonUtil;
import com.blog.news.utils.helper.InputMethodManagerUtils;
import com.rhino.ui.utils.Log;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

import static com.blog.news.ui.activity.shoppingshow.ShopingDetailsActivity.URL;

public class ShowNewsActivity extends BaseHttpActivity {
    @BindView(R.id.news_webview)
    WebView webView;
    @BindView(R.id.danmuview)
    DanmakuView mDanmakuView;
    @BindView(R.id.checkdanmu)
    ImageView checkdanmu;
    @BindView(R.id.danmutext)
    EditText danmutext;
    @BindView(R.id.senddanmu)
    ImageView senddanmu;
    private boolean isShowDanmu = true;
    private DanmakuContext mDanmakuContext;
    private HashMap<Integer, Integer> maxLinesPair;// 弹幕最大行数
    private HashMap<Integer, Boolean> overlappingEnablePair;// 设置是否重叠
    public static boolean isShow = true;
    private WebSettings webSettings;
    public final static String NewsID = "id";
    public final static String TITLE = "title";
    public final static String URl="url";
    private int postid;
    List<NewsListInfo.PostsBean.CommentsBean> commentsBeans;
    public final static String headers = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\"><style>\n" +
            "img{ max-width : 100%!important;height:auto;margin:0 auto;}</style> <body><script src=\"https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js\"></script></head>";
    public final static String footer = "</body><html>";
    private int colordata[] = {R.color.hong, R.color.cheng, R.color.huang, R.color.lv, R.color.qing
            , R.color.lan, R.color.zi, R.color.Violet, R.color.red1};
    public String comments;
    private   BaseDanmakuParser  mParser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    @Override
    protected void setContent() {
        setContentView(R.layout.activity_show_news);
//        AndroidBug5497Workaround.assistActivity(this);
//        Slidr.attach(this);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    @Override
    protected boolean initData() {
        commentsBeans = new ArrayList<>();

        return true;
    }

    /**
     * init the view
     */
    @Override
    protected void initView() {
        inButterKnifeView();
        setTitle(getIntent().getStringExtra(TITLE));
        initDanmaku();
        webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        if (null!=getIntent().getStringExtra(URl)){
            webView.loadUrl(getIntent().getStringExtra(URl));
        }else {
            initwebdata();
        }

    }

    private void initwebdata() {
        if (checkNetWork()) {
            doPost(UrlConstant.CHECK_NEWS_INFO, new NewsRequest(getIntent().getIntExtra(NewsID, 2)));
        }
    }

    @Override
    protected void CallbackHandler(int event, String url, String data, Object reqData) {
        super.CallbackHandler(event, url, data, reqData);
        switch (url) {
            case UrlConstant.CHECK_NEWS_INFO:
                GetNewsInfo(event, data);
                break;
            case UrlConstant.POST_COMMENT:
                PostCommentinfo(event,data);
            default:
                break;
        }
    }

    private void PostCommentinfo(int event, String data) {
        if (!isHttpRequestSuccess(event)){
            showAlert("评论失败");
        }else {
            Log.d("评论成功"+data);
        }
    }

    private void GetNewsInfo(int event, String data) {
        if (!isHttpRequestSuccess(event)) {
            showAlert("获取新闻详情失败");
        } else {
            NewsInfo newsInfo = GsonUtil.GsonToBean(data, NewsInfo.class);
            if (null != newsInfo) {
                comments = commentHtml(newsInfo.getPost().getComments());
                postid= newsInfo.getPost().getId();
                String a = headers + getNewContent(newsInfo.getPost().getContent()) + comments + footer;
                webView.loadDataWithBaseURL(null, a, "text/html", "utf-8", null);
                for (int i = 0; i < newsInfo.getPost().getComments().size(); i++) {
                    addDanmaku(false,newsInfo.getPost().getComments().get(i).getContent());
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void runActivity(Context context, int id, String titile) {
        Intent intent = new Intent(context, ShowNewsActivity.class);
        intent.putExtra(NewsID, id);
        intent.putExtra(TITLE, titile);
        context.startActivity(intent);
    }
    public static void runActivity(Context context, String url) {
        Intent intent = new Intent(context, ShowNewsActivity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }


    public String commentHtml(List<NewsInfo.PostBean.CommentsBean> commentsBeans) {
        String result = "";
        for (int i = 0; i < commentsBeans.size(); i++) {
            String a = "<p> <h6>" + (commentsBeans.get(i).getName()) + "</h6> <h5>" + (commentsBeans.get(i).getContent()) + "</h5><hr size=1></p>";
            result = result + a;

        }
        return result;
    }


    private void initDanmaku() {
        Log.d("初始化弹幕");
        mDanmakuContext = DanmakuContext.create();

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
        mDanmakuView.showFPS(false); //是否显示FPS
        mDanmakuView.enableDanmakuDrawingCache(true);
        if (mDanmakuView != null) {
            mDanmakuView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    Log.d("mDanmakuView.start();");
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
        if (mParser == null) {
            Log.d("mparser=null");
        }
        if (mDanmakuContext == null) {
            Log.d(mDanmakuContext + "mDanmakuContext");
        }

    }


    /**
     * 添加文本弹幕list
     *
     * @param islive  //是否是直播弹幕
     * @param comment //弹幕
     */
    @SuppressLint("NewApi")
    public void addDanmaku(boolean islive, String comment) {

        BaseDanmaku danmaku = mDanmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || mDanmakuView == null) {
            Log.d("danmaku" + danmaku + "mDanmakuView" + mDanmakuView);
//            Log.d("mDanmakuView"+mDanmakuView);
            return;
        }
        Log.d("弹幕" + comment);
        danmaku.text = comment;
        danmaku.padding = 5;
        danmaku.textSize = sp2px((int) (Math.random() * (40 - 20 + 1) + 20));
        danmaku.textColor = getColor(colordata[new Random().nextInt(colordata.length)]);
        danmaku.textShadowColor = Color.WHITE; //阴影/描边颜色
        if (islive) {
            danmaku.borderColor = danmaku.textColor;
        }

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
        Log.d("onPause");
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        Log.d("onResume");
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {

        Log.d("onDestroy");
        if (mParser != null) {
            mParser = null;
        }
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;

        }
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
        // TODO Auto-generated method stub
        InputMethodManagerUtils.fixInputMethodManagerLeak(this);
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        Log.d("onBackPressed");
        super.onBackPressed();
        if (mDanmakuView != null) {
            // dont forget release!
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }



    /**
     * 将html文本内容中包含img标签的图片，宽度变为屏幕宽度，高度根据宽度比例自适应
     **/
    public static String getNewContent(String htmltext) {
        try {
            Document doc = Jsoup.parse(htmltext);
            Elements elements = doc.getElementsByTag("img/gif");
            for (Element element : elements) {
                element.attr("width", "100%").attr("height", "auto");
            }
            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }



    @OnClick({R.id.checkdanmu, R.id.senddanmu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkdanmu:
                if (isShowDanmu) {
                    checkdanmu.setImageResource(R.drawable.danmuon);
                    mDanmakuView.show();
                } else {
                    checkdanmu.setImageResource(R.drawable.danmuoff);
                    mDanmakuView.hide();
                }
                isShowDanmu = !isShowDanmu;
                break;
            case R.id.senddanmu:
                if (!TextUtils.isEmpty(danmutext.getText().toString())) {
                    Log.d(danmutext.getText().toString());
                    addDanmaku(true, danmutext.getText().toString());
                    doPost(UrlConstant.POST_COMMENT,new Comment(postid,"超级大灰狼","123456@123.com",danmutext.getText().toString()));
                    danmutext.setText("");
                } else {
                    showAlert("不能发送空的弹幕");
                }
                break;
            default:
                break;
        }
    }
}


