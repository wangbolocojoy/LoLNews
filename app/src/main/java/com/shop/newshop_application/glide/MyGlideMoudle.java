package com.shop.newshop_application.glide;
import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
/**
 * Created by u on 2017/12/20.
 */
@GlideModule
public class MyGlideMoudle extends AppGlideModule {

        /**
         * 禁用清单解析
         * 这样可以改善 Glide 的初始启动时间，并避免尝试解析元数据时的一些潜在问题。
         *
         * @return
         */
        @Override
        public boolean isManifestParsingEnabled() {
            return false;
        }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        builder.setMemoryCache(new LruResourceCache(10*1024*1024));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);

    }


}
