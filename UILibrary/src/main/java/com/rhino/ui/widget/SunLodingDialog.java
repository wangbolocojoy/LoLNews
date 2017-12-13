package com.rhino.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rhino.u.R;

/**
 * Created by Unir|Superman
 * on 2017/12/8 18:09.
 * on Administrator
 * on NewShop_Application
 */

public class SunLodingDialog extends Dialog{

   private Activity mactivity;
   private SunBabyLoadingView sunBabyLoadingView;

    public SunLodingDialog(@NonNull Context context) {
        super(context);
        this.mactivity= (Activity) context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_sunloding_dialog);
        sunBabyLoadingView= (SunBabyLoadingView) findViewById(R.id.sunbabyloadingview);

    }
    @Override
    public void show() {
        if(null != mactivity && !mactivity.isFinishing()
                && !isShowing()){
            // 若软键盘打开，先关闭
            View view = mactivity.getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputmanger = (InputMethodManager) mactivity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            if(!isShowing()){
                super.show();
            }
        }

    }
}
