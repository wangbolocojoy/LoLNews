package com.blog.news.http.result.taoutiaoresult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Unir|Superman
 * on 2017/12/1 17:23.
 * on Administrator
 * on NewShop_Application
 */

public class TouTiaoListInfo implements Serializable {

    public static  String RS_OK = "000000";
    private boolean hasNext;
    private String retcode;
    private String appCode;
    private String dataType;
    private String pageToken;
    private List<DataBean> data;



    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "TouTiaoListInfo{" +
                "hasNext=" + hasNext +
                ", retcode='" + retcode + '\'' +
                ", appCode='" + appCode + '\'' +
                ", dataType='" + dataType + '\'' +
                ", pageToken='" + pageToken + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean extends TouTiaoListInfo{
        /**
         * posterId : null
         * catPathKey : 游戏
         * dislikeCount : 0
         * tags : ["药家鑫","藏尸案"]
         * publishDate : 1511968810
         * likeCount : 6
         * commentCount : 1
         * imageUrls : null
         * id : 6493856594483216654
         * viewCount : 109
         * posterScreenName : 头条问答
         * title : 上海“杀妻藏尸案”即将开庭，男方母亲称其还只是孩子求原谅，你认为该男子可以被原谅吗？
         * url : https://www.wukong.com/question/6493658259252052237
         * publishDateStr : 2017-11-29T15:20:10
         * content : 上海“杀妻藏尸案”即将开庭，男方母亲称其还只是孩子求原谅，你认为该男子可以被原谅吗？如果当场自首，可以选样原谅判个死缓。可杀人手段残忍，还藏尸冰箱三个月，还在房子共度三个月，证明他心里多么变态和阴暗，更是人性的泯灭和麻木。这种情况必须立即死刑。另外他妈说他还是一个孩子，这不是原谅的借口。法律永远凌驾于荒廖的借口之上。他不死，对陕西的药家鑫是不公平的。如果二人之间，我更感觉药家鑫的罪责要轻点。
         */

        private Object posterId;
        private String catPathKey;
        private int dislikeCount;
        private int publishDate;
        private int likeCount;
        private int commentCount;
        private List<String> imageUrls;
        private String id;
        private int viewCount;
        private String posterScreenName;
        private String title;
        private String url;
        private String publishDateStr;
        private String content;
        private List<String> tags;

        @Override
        public String toString() {
            return "DataBean{" +
                    "posterId=" + posterId +
                    ", catPathKey='" + catPathKey + '\'' +
                    ", dislikeCount=" + dislikeCount +
                    ", publishDate=" + publishDate +
                    ", likeCount=" + likeCount +
                    ", commentCount=" + commentCount +
                    ", imageUrls=" + imageUrls +
                    ", id='" + id + '\'' +
                    ", viewCount=" + viewCount +
                    ", posterScreenName='" + posterScreenName + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", publishDateStr='" + publishDateStr + '\'' +
                    ", content='" + content + '\'' +
                    ", tags=" + tags +
                    '}';
        }

        public Object getPosterId() {
            return posterId;
        }

        public void setPosterId(Object posterId) {
            this.posterId = posterId;
        }

        public String getCatPathKey() {
            return catPathKey;
        }

        public void setCatPathKey(String catPathKey) {
            this.catPathKey = catPathKey;
        }

        public int getDislikeCount() {
            return dislikeCount;
        }

        public void setDislikeCount(int dislikeCount) {
            this.dislikeCount = dislikeCount;
        }

        public int getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(int publishDate) {
            this.publishDate = publishDate;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public List<String> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public String getPosterScreenName() {
            return posterScreenName;
        }

        public void setPosterScreenName(String posterScreenName) {
            this.posterScreenName = posterScreenName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPublishDateStr() {
            return publishDateStr;
        }

        public void setPublishDateStr(String publishDateStr) {
            this.publishDateStr = publishDateStr;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
}
