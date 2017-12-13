package com.shop.newshop_application.utils.helper;

import android.app.Activity;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Unir|Superman
 * on 2017/12/5 12:32.
 * on Administrator
 * on NewShop_Application
 */

public interface ImageLoader extends Serializable{

    void displayImage(Activity activity, String path, ImageView imageView, int width, int height);

    void clearMemoryCache();
}
