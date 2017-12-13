package com.rhino.ui.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


/**
 * Created by wb
 * on 2017/11/21
 * on AlertToast
 */
public class AlertToast {

	/**
	 * the toast for normal
	 */
	private static Toast mToast;
	/**
	 * the toast for center
	 */
	private static Toast mToastCenter;
	
	
	/**
	 * show normal toast
	 * @param context the context
	 * @param msg the toast message
	 */
	public static void show(Context context, CharSequence msg) {
		try {
			if (mToast == null) {
				mToast = Toast.makeText(context.getApplicationContext() , msg, Toast.LENGTH_SHORT);
	        } else {
	        	mToast.setDuration(Toast.LENGTH_SHORT);
	        	mToast.setText(msg);
	        }
			mToast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * show center toast
	 * @param context the context
	 * @param msg the toast message
	 */
	public static void showCenter(Context context, CharSequence msg) {
		try {
			if (mToastCenter == null) {
				mToastCenter = Toast.makeText(context.getApplicationContext() , msg, Toast.LENGTH_SHORT);
				mToastCenter.setGravity(Gravity.CENTER, 0, 0);
	        } else {
	        	mToastCenter.setDuration(Toast.LENGTH_SHORT);
	        	mToastCenter.setText(msg);
	        }
			mToastCenter.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * show normal toast
	 * @param context the context
	 * @param view the toast view
	 */
	public static void show(Context context, View view) {
		try {
			if(null == mToast){
				mToast = new Toast(context);
				mToast.setDuration(Toast.LENGTH_SHORT);
			}
			mToast.setView(view);
			mToast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * show center toast
	 * @param context the context
	 * @param view the toast view
	 */
	public static void showCenter(Context context, View view) {
		try {
			if(null == mToastCenter){
				mToastCenter = new Toast(context);
				mToastCenter.setDuration(Toast.LENGTH_SHORT);
				mToastCenter.setGravity(Gravity.CENTER, 0, 0);
			}
			mToastCenter.setView(view);
			mToastCenter.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
