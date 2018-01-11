package com.shop.newshop_application.constant;

import com.shop.newshop_application.http.request.regster.SmsCaptcha;

/**
 * Created by Unir|Superman
 * on 2017/11/22 10:33.
 * on Administrator
 * on NewShop_Application
 */

public class UrlConstant {

    /**
     * 本地服务器
     */
//    public static final String  FAINLAPI="http://192.168.1.6:8088/";

    /**
     *
     */
    public static final String  FAINLAPI="http://09TU0H1PGM25U9N:8040/";

    /**
     * 发送验证码{@link SmsCaptcha}
     */
    public static final String  REGISTERSENDMES=FAINLAPI+"user/regist/sendSmsCode";
    /**
     * 手机注册{参数 @link SmsCaptcha} {返回 @link}
     */
    public static final String  REGISTERBYPHONE=FAINLAPI+"/user/regist/phone";
    /**
     * 手机登陆{参数 @link SmsCaptcha} {返回 @link}
     */
    public static final String  LOGINBYPHONE=FAINLAPI+"/user/login";
    /**
     * 获取全部商品列表
     */
    public static final String  CHECKALLSHOPPINGLIST=FAINLAPI+"item/list";

    /**
     * 获取茶窝网商品详情
     */
    public static final String  CHECKALLSHOPPINGDETAILINFO="http://www.chawo.com/mobile/index.php";
    public static final String  CHECKALLSHOPPINGLISTCHAWO="http://www.chawo.com/mobile/index.php?act=index";


    public static String catid;
    public static final String  API="http://120.76.205.241:8000/news/toutiao";
    public static final String  LOLVIDEOAPI="http://apps.game.qq.com/lol/act/website2013/video.php?";
    public static final String  LOLNEWSAPI="http://qt.qq.com/php_cgi/news/php/varcache_getnews.php?";

}
