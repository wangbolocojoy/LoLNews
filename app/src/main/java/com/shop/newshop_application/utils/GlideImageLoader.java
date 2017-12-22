/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shop.newshop_application.utils;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.ninegrid.NineGridView;
import com.shop.newshop_application.glide.GlideApp;
import com.shop.newshop_application.utils.helper.ImageLoader;

import java.io.File;


public class GlideImageLoader implements ImageLoader, NineGridView.ImageLoader{
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
       GlideApp.with(context).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(url).skipMemoryCache(true).into(imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideApp.with(activity).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).load(new File(path)).skipMemoryCache(true).into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
