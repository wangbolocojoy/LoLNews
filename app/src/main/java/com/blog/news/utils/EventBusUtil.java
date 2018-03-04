package com.blog.news.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by SuperDaHuiLang on 2018/3/1.
 */

public class EventBusUtil {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }
    public static void sendeventid(int id){
        EventBus.getDefault().post(id);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
