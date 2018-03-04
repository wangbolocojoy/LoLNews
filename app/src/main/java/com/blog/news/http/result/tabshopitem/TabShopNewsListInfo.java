package com.blog.news.http.result.tabshopitem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Unir|Superman
 * on 2017/12/7 17:21.
 * on Administrator
 * on NewShop_Application
 */

public class TabShopNewsListInfo implements Serializable {

    private String next;
    private String nextpage;
    private List<ListBean> list;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getNextpage() {
        return nextpage;
    }

    public void setNextpage(String nextpage) {
        this.nextpage = nextpage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * article_id : 41162
         * content_id : 41162
         * newstype :
         * newstypeid : ordinary
         * channel_desc :
         * channel_id : <2>:<10>,<2>:<12>
         * insert_date : 2017-12-01 20:38:32
         * title : 掌盟大神团：上路霸主难撼动，季前赛鳄鱼王者教学
         * article_url : http://qt.qq.com/php_cgi/news/php/varcache_article.php?id=41162&version=$PROTO_VERSION$&areaid=$REGION$
         * summary : 这一期我们的重点是：S8季前赛鳄鱼教学。
         * score : 3
         * publication_date : 2017-12-01 20:16:49
         * targetid : 1
         * intent :
         * is_act : 0
         * is_hot : 0
         * is_subject : 0
         * is_new : 0
         * is_top : False
         * image_with_btn : False
         * image_spec : 1
         * is_report : True
         * is_direct : False
         * image_url_small : http://ossweb-img.qq.com/upload/qqtalk/news/201712/012038323001743_282.jpg
         * image_url_big : http://ossweb-img.qq.com/upload/qqtalk/news/201712/012038323001743_480.jpg
         * image_url_bigop : http://ossweb-img.qq.com/upload/qqtalk/news/201712/012038323001743_686.jpg
         * image_url_act :
         * pv : 98044
         * bmatchid : 0
         * v_len :
         * pics_id : 0
         * is_purchase : 0
         * commentid : 41162
         * actnews_enddate : 2018-12-01 20:16:49
         * actnews_reward :
         * doc_id : 14526381119637340141
         * author : 掌盟@东门剑姬
         */

        private String article_id;
        private String content_id;
        private String newstype;
        private String newstypeid;
        private String channel_desc;
        private String channel_id;
        private String insert_date;
        private String title;
        private String article_url;
        private String summary;
        private String score;
        private String publication_date;
        private String targetid;
        private String intent;
        private String is_act;
        private String is_hot;
        private String is_subject;
        private String is_new;
        private String is_top;
        private String image_with_btn;
        private String image_spec;
        private String is_report;
        private String is_direct;
        private String image_url_small;
        private String image_url_big;
        private String image_url_bigop;
        private String image_url_act;
        private String pv;
        private String bmatchid;
        private String v_len;
        private String pics_id;
        private String is_purchase;
        private String commentid;
        private String actnews_enddate;
        private String actnews_reward;
        private String doc_id;
        private String author;

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getNewstype() {
            return newstype;
        }

        public void setNewstype(String newstype) {
            this.newstype = newstype;
        }

        public String getNewstypeid() {
            return newstypeid;
        }

        public void setNewstypeid(String newstypeid) {
            this.newstypeid = newstypeid;
        }

        public String getChannel_desc() {
            return channel_desc;
        }

        public void setChannel_desc(String channel_desc) {
            this.channel_desc = channel_desc;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public String getInsert_date() {
            return insert_date;
        }

        public void setInsert_date(String insert_date) {
            this.insert_date = insert_date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getPublication_date() {
            return publication_date;
        }

        public void setPublication_date(String publication_date) {
            this.publication_date = publication_date;
        }

        public String getTargetid() {
            return targetid;
        }

        public void setTargetid(String targetid) {
            this.targetid = targetid;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public String getIs_act() {
            return is_act;
        }

        public void setIs_act(String is_act) {
            this.is_act = is_act;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getIs_subject() {
            return is_subject;
        }

        public void setIs_subject(String is_subject) {
            this.is_subject = is_subject;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getImage_with_btn() {
            return image_with_btn;
        }

        public void setImage_with_btn(String image_with_btn) {
            this.image_with_btn = image_with_btn;
        }

        public String getImage_spec() {
            return image_spec;
        }

        public void setImage_spec(String image_spec) {
            this.image_spec = image_spec;
        }

        public String getIs_report() {
            return is_report;
        }

        public void setIs_report(String is_report) {
            this.is_report = is_report;
        }

        public String getIs_direct() {
            return is_direct;
        }

        public void setIs_direct(String is_direct) {
            this.is_direct = is_direct;
        }

        public String getImage_url_small() {
            return image_url_small;
        }

        public void setImage_url_small(String image_url_small) {
            this.image_url_small = image_url_small;
        }

        public String getImage_url_big() {
            return image_url_big;
        }

        public void setImage_url_big(String image_url_big) {
            this.image_url_big = image_url_big;
        }

        public String getImage_url_bigop() {
            return image_url_bigop;
        }

        public void setImage_url_bigop(String image_url_bigop) {
            this.image_url_bigop = image_url_bigop;
        }

        public String getImage_url_act() {
            return image_url_act;
        }

        public void setImage_url_act(String image_url_act) {
            this.image_url_act = image_url_act;
        }

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getBmatchid() {
            return bmatchid;
        }

        public void setBmatchid(String bmatchid) {
            this.bmatchid = bmatchid;
        }

        public String getV_len() {
            return v_len;
        }

        public void setV_len(String v_len) {
            this.v_len = v_len;
        }

        public String getPics_id() {
            return pics_id;
        }

        public void setPics_id(String pics_id) {
            this.pics_id = pics_id;
        }

        public String getIs_purchase() {
            return is_purchase;
        }

        public void setIs_purchase(String is_purchase) {
            this.is_purchase = is_purchase;
        }

        public String getCommentid() {
            return commentid;
        }

        public void setCommentid(String commentid) {
            this.commentid = commentid;
        }

        public String getActnews_enddate() {
            return actnews_enddate;
        }

        public void setActnews_enddate(String actnews_enddate) {
            this.actnews_enddate = actnews_enddate;
        }

        public String getActnews_reward() {
            return actnews_reward;
        }

        public void setActnews_reward(String actnews_reward) {
            this.actnews_reward = actnews_reward;
        }

        public String getDoc_id() {
            return doc_id;
        }

        public void setDoc_id(String doc_id) {
            this.doc_id = doc_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
