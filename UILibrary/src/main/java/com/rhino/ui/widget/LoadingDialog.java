package com.rhino.ui.widget;

import com.rhino.u.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


/**
 * Created by wb
 * on 2017/11/21
 * on LoadingDialog 
 */
public class LoadingDialog extends Dialog {

	private Activity mActivity;
	private TextView mTextView;
	private String text = "加载中...";
	
    public LoadingDialog(Activity activity) {
        super(activity, R.style.CustomDialog);
        this.mActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_loading_dialog);
        mTextView = (TextView) findViewById(R.id.tv);
        mTextView.setText(text);
    }
    
    @Override
    public void show() {
    	if(null != mActivity && !mActivity.isFinishing()
				&& !isShowing()){
    		// 若软键盘打开，先关闭
    		View view = mActivity.getWindow().peekDecorView();
            if (view != null) {
                InputMethodManager inputmanger = (InputMethodManager) mActivity
                		.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            if(!isShowing()){
            	super.show();
            }
    	}
    }
    
    @Override
    public void dismiss() {
    	if(null != mActivity && !mActivity.isFinishing() 
    			&& isShowing()){
    		super.dismiss();
    	}
    }
    
    public LoadingDialog setText(String text){
    	this.text = text;
    	if(null != mTextView){
    		mTextView.setText(text);
    	}
    	return this;
    }

}
