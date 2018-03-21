package com.blog.news.constant;

import com.blog.news.http.request.blog.NewsRequest;
import com.blog.news.http.request.regster.SmsCaptcha;

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
    public static final String FAINLAPI = "http://09TU0H1PGM25U9N:8040/";
    public static final String BLOGAPI="http://www.hellobtm.com/api/";
    /**
     * 发送验证码{@link SmsCaptcha}
     */
    public static final String REGISTERSENDMES = FAINLAPI + "user/regist/sendSmsCode";
    /**
     * 手机注册{参数 @link SmsCaptcha} {返回 @link}
     */
    public static final String REGISTERBYPHONE = FAINLAPI + "/user/regist/phone";
    /**
     * 手机登陆{参数 @link SmsCaptcha} {返回 @link}
     */
    public static final String LOGINBYPHONE = FAINLAPI + "/user/login";
    /**
     * 获取全部商品列表
     */
    public static final String CHECKALLSHOPPINGLIST = FAINLAPI + "item/list";

    /**
     * 获取茶窝网商品详情
     */
    public static final String CHECKALLSHOPPINGDETAILINFO = "http://www.chawo.com/mobile/index.php";
    public static final String CHECKALLSHOPPINGLISTCHAWO = "http://www.chawo.com/mobile/index.php?act=index";
    public static final String ChCEK_DOUYIN = "https://aweme.snssdk.com/aweme/v1/feed/?iid=24520696851&device_id=38006679787&os_api=18&app_name=aweme&channel=App%20Store&idfa=00000000-0000-0000-0000-000000000000&device_platform=iphone&build_number=17201&vid=70A6B1E7-FE2A-4B33-A7CE-98388A65DD9B&openudid=5117de8a206bfffaf895448a3293bdfb6c5c1de1&device_type=iPhone7,2&app_version=1.7.2&version_code=1.7.2&os_version=11.3&screen_width=750&aid=1128&ac=WIFI&count=6&feed_style=0&min_cursor=0&pull_type=1&type=0&volume=0.00&as=a145d83763967ac812&ts=1517455459";
    public static final String API = "http://120.76.205.241:8000/news/toutiao";
    public static final String LOLVIDEOAPI = "http://apps.game.qq.com/lol/act/website2013/video.php?";
    public static final String LOLNEWSAPI = "http://qt.qq.com/php_cgi/news/php/varcache_getnews.php";

    /**
     * 获取blog新闻类型
     */
    public static final String NEWS_TYPE=BLOGAPI+"get_category_index/";
    /**
     * 获取blog新闻列表
     * {@link NewsRequest}
     */
    public static final String NEWS_TITELLIST=BLOGAPI+"get_category_posts/";

    /**
     * 获取新闻详情
     * {@link com.blog.news.http.result.blog.NewsInfo}
     */
    public static final String CHECK_NEWS_INFO=BLOGAPI+"get_post/";

    /**
     * 发表评论
     */
    public static final String POST_COMMENT=BLOGAPI+"respond/submit_comment/";
}
