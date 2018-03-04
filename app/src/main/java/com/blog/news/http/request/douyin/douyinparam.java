package com.blog.news.http.request.douyin;

import com.blog.news.http.ParamField;
import com.blog.news.http.request.HttpParams;

/**
 * Created by MacBookPor on 2018/1/31.
 */

public class douyinparam  implements HttpParams{
    @ParamField("max_cursor")
    private int max_cursor;
    @ParamField("min_cursor")
    private int min_cursor;
    @ParamField("count")
    private int count;
    @ParamField("feed_style")
    private int feed_style;
    @ParamField("retry_type")
    private String retry_type;
    @ParamField("iid")
    private String iid;
    @ParamField("device_id")
    private String device_id;
    @ParamField("ac")
    private String ac;
    @ParamField("channel")
    private String channel;
    @ParamField("aid")
    private int aid;
    @ParamField("app_name")
    private String app_name;
    @ParamField("version_code")
    private int version_code;
    @ParamField("version_name")
    private String version_name;
    @ParamField("device_platform")
    private String device_platform;
    @ParamField("ssmix")
    private String ssmix;
    @ParamField("device_type")
    private String device_type;
    @ParamField("device_brand")
    private String device_brand;
    @ParamField("language")
    private String language;
    @ParamField("os_api")
    private int os_api;
    @ParamField("os_version")
    private String os_version;
    @ParamField("uuid")
    private String uuid;
    @ParamField("openudid")
    private String openudid;
    @ParamField("manifest_version_code")
    private int manifest_version_code;
    @ParamField("resolution")
    private String resolution;
    @ParamField("dpi")
    private int dpi;
    @ParamField("update_version_code")
    private int update_version_code;
    @ParamField("_rticket")
    private String _rticket;
    @ParamField("ts")
    private long ts;
    @ParamField("as")
    private String as;
    @ParamField("cp")
    private String cp;
    @ParamField("mas")
    private String mas;

    public douyinparam(String as, String cp, String mas) {
        this.max_cursor = 0 ;
        this.min_cursor = 0;
        this.count = 20;
        this.feed_style = 1;
        this.retry_type = "no_retry";
        this.iid = "24717101284";
        this.device_id = "47564735399";
        this.ac = "wifi";
        this.channel = "oppo";
        this.aid = 1128;
        this.app_name = "aweme";
        this.version_code = 172;
        this.version_name = "1.7.2";
        this.device_platform = "android";
        this.ssmix = "a";
        this.device_type = "OPPO+R11st";
        this.device_brand = "OPPO";
        this.language = "zh";
        this.os_api = 25;
        this.os_version = "7.1.1";
        this.uuid = "867465038349277";
        this.openudid = "e0ec851c8b6df11a";
        this.manifest_version_code = 172;
        this.resolution = "1080*2160";
        this.dpi = 480;
        this.update_version_code = 1722;
        this._rticket = "1517393925006";
        this.ts = 1517393924;
        this.as = as;
        this.cp = cp;
        this.mas = mas;
    }

    public int getMax_cursor() {
        return max_cursor;
    }

    public void setMax_cursor(int max_cursor) {
        this.max_cursor = max_cursor;
    }

    public int getMin_cursor() {
        return min_cursor;
    }

    public void setMin_cursor(int min_cursor) {
        this.min_cursor = min_cursor;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFeed_style() {
        return feed_style;
    }

    public void setFeed_style(int feed_style) {
        this.feed_style = feed_style;
    }

    public String getRetry_type() {
        return retry_type;
    }

    public void setRetry_type(String retry_type) {
        this.retry_type = retry_type;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getDevice_platform() {
        return device_platform;
    }

    public void setDevice_platform(String device_platform) {
        this.device_platform = device_platform;
    }

    public String getSsmix() {
        return ssmix;
    }

    public void setSsmix(String ssmix) {
        this.ssmix = ssmix;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_brand() {
        return device_brand;
    }

    public void setDevice_brand(String device_brand) {
        this.device_brand = device_brand;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getOs_api() {
        return os_api;
    }

    public void setOs_api(int os_api) {
        this.os_api = os_api;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOpenudid() {
        return openudid;
    }

    public void setOpenudid(String openudid) {
        this.openudid = openudid;
    }

    public int getManifest_version_code() {
        return manifest_version_code;
    }

    public void setManifest_version_code(int manifest_version_code) {
        this.manifest_version_code = manifest_version_code;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public int getUpdate_version_code() {
        return update_version_code;
    }

    public void setUpdate_version_code(int update_version_code) {
        this.update_version_code = update_version_code;
    }

    public String get_rticket() {
        return _rticket;
    }

    public void set_rticket(String _rticket) {
        this._rticket = _rticket;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getMas() {
        return mas;
    }

    public void setMas(String mas) {
        this.mas = mas;
    }
}
