package com.shop.newshop_application.application;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.lzy.ninegrid.NineGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;

import com.lzy.okgo.cookie.store.SPCookieStore;
import com.rhino.ui.utils.CrashHandler;
import com.rhino.ui.utils.FileUtils;
import com.rhino.ui.utils.Log;
import com.shop.newshop_application.http.HttpRequest;
import com.shop.newshop_application.utils.GlideImageLoader;
import com.shop.newshop_application.utils.MyUtils;
import com.shop.newshop_application.utils.helper.Config;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;

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
    /** 单例的application对象 **/
    private static MyApplication app;
    public static MyApplication getInstance() {
        if (app == null) {
            app = new MyApplication();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mContext = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
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

        Utils.init(this);
        CrashUtils.init();
        initDebug(mContext);

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
            LogUtils.getConfig().setLogSwitch(true);
            Log.getInstance().init(context, true, true);
        }else{
            LogUtils.getConfig().setLogSwitch(false);
            Log.getInstance().init(context, debugVersion, false);
        }
    }
    /**
     * 初始话崩溃处理器，输出崩溃日志文件到手机内存，方便手机上查看
     */
    private void initCrashHandler() {
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


}
