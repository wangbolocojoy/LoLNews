package com.blog.news.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.blog.news.ui.activity.MainTabActivicty;

/**
 * <p>界面跳转的一些工具类</p>
 * Create by LuoLin on 2016年12月06日
 */
public class UiUtils {

    /** 避免重复进入主页 **/
    public static boolean isEnteredMainPage = false;

	public static void showPage(@NonNull Context context, @NonNull Class<?> cls, Bundle extras) {
		Intent intent = new Intent(context, cls);
		if (!(context instanceof Activity)) {
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		if (null != extras)
			intent.putExtras(extras);
		context.startActivity(intent);
	}

	public static void showPageForResult(@NonNull Activity activity, @NonNull Class<?> cls,
                                         Bundle extras, int resultCode) {
		Intent intent = new Intent(activity, cls);
		if (!(activity instanceof Activity)) {
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
		if (null != extras)
			intent.putExtras(extras);
		activity.startActivityForResult(intent, resultCode);
	}

	public static void showMainPage(@NonNull Activity activity, Bundle extras){
		showPage(activity, MainTabActivicty.class, extras);
	}




}
