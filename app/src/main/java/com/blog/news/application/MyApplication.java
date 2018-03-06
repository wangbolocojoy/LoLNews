package com.blog.news.application;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.blog.news.R;
import com.blog.news.utils.ThreadManager;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lzy.ninegrid.NineGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;

import com.lzy.okgo.cookie.store.SPCookieStore;
import com.rhino.ui.utils.CrashHandler;
import com.rhino.ui.utils.FileUtils;
import com.rhino.ui.utils.Log;
import com.blog.news.http.HttpRequest;
import com.blog.news.utils.GlideImageLoader;
import com.blog.news.utils.MyUtils;
import com.blog.news.utils.helper.Config;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;


/**
 * Created by wb
 * on 2017/11/21 17:36
 *
 */
public class MyApplication extends MultiDexApplication {

    /** 服务 */
    private Intent mIntent;
    /** 上下文 */
    private Context mContext;
    /** http请求工具类 **/
    /** http请求工具类 **/
    private HttpRequest mHttpRequest;
    private  ThreadManager mthreadManager;
    public static List<Object>ShoppingCar;
    /** 单例的application对象 **/
    private static MyApplication app;
    public static MyApplication getInstance() {
        if (app == null) {
            app = new MyApplication();
        }
        return app;
    }
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mContext = getApplicationContext();
        mFirebaseAnalytics =FirebaseAnalytics.getInstance(this);
        MobileAds.initialize(this, "ca-app-pub-3405017856175243~8989439445");
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        inintTXX5Web();
        LeakCanary.install(this);
        // Normal app init code...
        NineGridView.setImageLoader(new GlideImageLoader());
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));

        //使用数据库保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));
//        //使用内存保持cookie，app退出后，cookie消失
//        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
        OkGo.getInstance().init(this)

                .setOkHttpClient(builder.build());
        mHttpRequest = new HttpRequest(mContext);
        initCrashHandler();
        Utils.init(this);
        CrashUtils.init();
        initDebug(mContext);
        ShoppingCar=new ArrayList<>();

    }


    /**
     * 获取HttpRequest单列
     * @return HTTP请求工具类
     */
    public HttpRequest getHttpRequest() {
        if (mHttpRequest == null) {
            mHttpRequest = new HttpRequest(app.getApplicationContext());
        }
        return mHttpRequest;
    }

    /**
     * 停止服务
     */
    public void stopService() {
        stopService(mIntent);
    }

    /**
     * 开始服务
     */
    public void startService() {
        if (null != mIntent) {
            startService(mIntent);
        }
    }
    /**
     * 获取context
     * @return 上下文
     */
    public static Context getContext() {
        return getInstance().mContext;
    }
    /**
     * 初始化日志系统
     * @param context 上下文
     */
    private void initDebug(Context context) {


        boolean debugVersion = MyUtils.isApkDebugVersion(context);
        boolean debug = Config.getInstance().isDebugMode();
        if(debug){
            Log.d("DEBUG版本");
            LogUtils.getConfig().setLogSwitch(true);
            Log.getInstance().init(context, true, true);
        }else{
            Log.d("正式版");
            LogUtils.getConfig().setLogSwitch(false);
            Log.getInstance().init(context, debugVersion, false);
        }
    }
    /**
     * 初始话崩溃处理器，输出崩溃日志文件到手机内存，方便手机上查看
     */
    private void initCrashHandler() {
        Log.d("初始化崩溃日志记录");
        String dirPath;
        if (FileUtils.hasSdcard()) {
            dirPath = Environment.getExternalStorageDirectory().getPath()
                    + File.separator + getPackageName();
        } else {
            dirPath = getFilesDir().getPath();
        }
        CrashHandler.getInstance().init(getApplicationContext(), dirPath,
                "debug.txt", !MyUtils.isApkDebugVersion(getContext()));
    }

    private  void inintTXX5Web(){
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d( " 初始化X5内核onViewInitFinished is " + b);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };

//x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }


}
