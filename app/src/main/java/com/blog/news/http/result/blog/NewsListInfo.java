package com.blog.news.http.result.blog;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author MacBookPor
 * @date 2018/2/24
 */

public class NewsListInfo implements Serializable {
    /**
     * status : ok
     * count : 2
     * pages : 1
     * category : {"id":2,"slug":"tuijian","title":"推荐","description":"","parent":0,"post_count":2}
     * posts : [{"id":23,"type":"post","slug":"wordjksna","url":"http://www.hellobtm.com/tuijian/23/2018/02/08/","status":"publish","title":"建站经历","title_plain":"建站经历","content":"<p>今天终于建好网站了&#x1f606;<\/p>\n<p>下面我就说下建站的坎坷经历，17年12月份的时候在阿里买了个虚拟主机，域名也买好了，然后域名需要备案，那就备案吧，拍照，填资料，邮寄到阿里，结果还是没过最后一关，现在这备案啊，不是你想备就能备的，网站名字不能这样，不能那样，等了20多天审核还是没过，告诉我需要去省会互联网新闻办公室背书&#x1f622;。。。。。 现在重新提交审核了，<\/p>\n<p>然后实在是不想再多等20多天，直接买了个香港的虚拟主机，不用备案，  一小时就搞定了&#x1f60e;<\/p>\n<p>最近我会上架同步这个网站的Android端app   iOS客户端的上传到AppStore 需要99美元&#x1f62d;，暂时还没想法<\/p>\n<p>&nbsp;<\/p>\n","excerpt":"<p>今天终于建好网站了&#x1f606; 下面我就说下建站的坎坷经历，17年12月份的时候在阿里买了个虚拟主机，域 [&hellip;]<\/p>\n","date":"2018-02-08 13:16:20","modified":"2018-02-08 13:17:57","categories":[{"id":2,"slug":"tuijian","title":"推荐","description":"","parent":0,"post_count":2}],"tags":[],"author":{"id":1,"slug":"wangbo","name":"wangbo","first_name":"","last_name":"","nickname":"wangbo","url":"","description":""},"comments":[{"id":2,"name":"adasd","url":"","date":"2018-02-10 10:05:47","content":"<p>facade<\/p>\n","parent":0},{"id":3,"name":"好听","url":"http://dfhhfc","date":"2018-02-14 12:02:50","content":"<p>哎呦！不错哟<\/p>\n","parent":0}],"attachments":[],"comment_count":2,"comment_status":"open","custom_fields":{"np_single_post_sidebar":["default_sidebar"],"post_meta_identity":["np-metabox-info"]}}]
     */

    private String status;
    private int count;
    private int pages;
    private CategoryBean category;
    private List<PostsBean> posts;

    @Override
    public String toString() {
        return "NewsListInfo{" +
                "status='" + status + '\'' +
                ", count=" + count +
                ", pages=" + pages +
                ", category=" + category +
                ", posts=" + posts +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class CategoryBean implements Serializable{
        /**
         * id : 2
         * slug : tuijian
         * title : 推荐
         * description :
         * parent : 0
         * post_count : 2
         */

        private int id;
        private String slug;
        private String title;
        private String description;
        private int parent;
        private int post_count;

        @Override
        public String toString() {
            return "CategoryBean{" +
                    "id=" + id +
                    ", slug='" + slug + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", parent=" + parent +
                    ", post_count=" + post_count +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public int getPost_count() {
            return post_count;
        }

        public void setPost_count(int post_count) {
            this.post_count = post_count;
        }
    }

    public static class PostsBean implements Serializable{
        /**
         * id : 23
         * type : post
         * slug : wordjksna
         * url : http://www.hellobtm.com/tuijian/23/2018/02/08/
         * status : publish
         * title : 建站经历
         * title_plain : 建站经历
         * content : <p>今天终于建好网站了&#x1f606;</p>
         <p>下面我就说下建站的坎坷经历，17年12月份的时候在阿里买了个虚拟主机，域名也买好了，然后域名需要备案，那就备案吧，拍照，填资料，邮寄到阿里，结果还是没过最后一关，现在这备案啊，不是你想备就能备的，网站名字不能这样，不能那样，等了20多天审核还是没过，告诉我需要去省会互联网新闻办公室背书&#x1f622;。。。。。 现在重新提交审核了，</p>
         <p>然后实在是不想再多等20多天，直接买了个香港的虚拟主机，不用备案，  一小时就搞定了&#x1f60e;</p>
         <p>最近我会上架同步这个网站的Android端app   iOS客户端的上传到AppStore 需要99美元&#x1f62d;，暂时还没想法</p>
         <p>&nbsp;</p>

         * excerpt : <p>今天终于建好网站了&#x1f606; 下面我就说下建站的坎坷经历，17年12月份的时候在阿里买了个虚拟主机，域 [&hellip;]</p>

         * date : 2018-02-08 13:16:20
         * modified : 2018-02-08 13:17:57
         * categories : [{"id":2,"slug":"tuijian","title":"推荐","description":"","parent":0,"post_count":2}]
         * tags : []
         * author : {"id":1,"slug":"wangbo","name":"wangbo","first_name":"","last_name":"","nickname":"wangbo","url":"","description":""}
         * comments : [{"id":2,"name":"adasd","url":"","date":"2018-02-10 10:05:47","content":"<p>facade<\/p>\n","parent":0},{"id":3,"name":"好听","url":"http://dfhhfc","date":"2018-02-14 12:02:50","content":"<p>哎呦！不错哟<\/p>\n","parent":0}]
         * attachments : []
         * comment_count : 2
         * comment_status : open
         * custom_fields : {"np_single_post_sidebar":["default_sidebar"],"post_meta_identity":["np-metabox-info"]}
         */

        private int id;
        private String type;
        private String slug;
        private String url;
        private String status;
        private String title;
        private String title_plain;
        private String excerpt;
        private String date;
        private String modified;
        private AuthorBean author;
        private int comment_count;
        private String comment_status;
        private CustomFieldsBean custom_fields;
        private List<CategoriesBean> categories;
        private List<CommentsBean> comments;

        @Override
        public String toString() {
            return "PostsBean{" +
                    "id=" + id +
                    ", type='" + type + '\'' +
                    ", slug='" + slug + '\'' +
                    ", url='" + url + '\'' +
                    ", status='" + status + '\'' +
                    ", title='" + title + '\'' +
                    ", title_plain='" + title_plain + '\'' +
                    ", excerpt='" + excerpt + '\'' +
                    ", date='" + date + '\'' +
                    ", modified='" + modified + '\'' +
                    ", author=" + author +
                    ", comment_count=" + comment_count +
                    ", comment_status='" + comment_status + '\'' +
                    ", custom_fields=" + custom_fields +
                    ", categories=" + categories +
                    ", comments=" + comments +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_plain() {
            return title_plain;
        }

        public void setTitle_plain(String title_plain) {
            this.title_plain = title_plain;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getComment_status() {
            return comment_status;
        }

        public void setComment_status(String comment_status) {
            this.comment_status = comment_status;
        }

        public CustomFieldsBean getCustom_fields() {
            return custom_fields;
        }

        public void setCustom_fields(CustomFieldsBean custom_fields) {
            this.custom_fields = custom_fields;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class AuthorBean implements Serializable{
            /**
             * id : 1
             * slug : wangbo
             * name : wangbo
             * first_name :
             * last_name :
             * nickname : wangbo
             * url :
             * description :
             */

            private int id;
            private String slug;
            private String name;
            private String first_name;
            private String last_name;
            private String nickname;
            private String url;
            private String description;

            @Override
            public String toString() {
                return "AuthorBean{" +
                        "id=" + id +
                        ", slug='" + slug + '\'' +
                        ", name='" + name + '\'' +
                        ", first_name='" + first_name + '\'' +
                        ", last_name='" + last_name + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", url='" + url + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class CustomFieldsBean {
            private List<String> np_single_post_sidebar;
            private List<String> post_meta_identity;

            public List<String> getNp_single_post_sidebar() {
                return np_single_post_sidebar;
            }

            public void setNp_single_post_sidebar(List<String> np_single_post_sidebar) {
                this.np_single_post_sidebar = np_single_post_sidebar;
            }

            public List<String> getPost_meta_identity() {
                return post_meta_identity;
            }

            public void setPost_meta_identity(List<String> post_meta_identity) {
                this.post_meta_identity = post_meta_identity;
            }
        }

        public static class CategoriesBean implements Serializable{
            /**
             * id : 2
             * slug : tuijian
             * title : 推荐
             * description :
             * parent : 0
             * post_count : 2
             */

            private int id;
            private String slug;
            private String title;
            private String description;
            private int parent;
            private int post_count;

            @Override
            public String toString() {
                return "CategoriesBean{" +
                        "id=" + id +
                        ", slug='" + slug + '\'' +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", parent=" + parent +
                        ", post_count=" + post_count +
                        '}';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getParent() {
                return parent;
            }

            public void setParent(int parent) {
                this.parent = parent;
            }

            public int getPost_count() {
                return post_count;
            }

            public void setPost_count(int post_count) {
                this.post_count = post_count;
            }
        }

        public static class CommentsBean implements Serializable{
            /**
             * id : 2
             * name : adasd
             * url :
             * date : 2018-02-10 10:05:47
             * content : <p>facade</p>

             * parent : 0
             */

            private int id;
            private String name;
            private String url;
            private String date;
            private String content;
            private int parent;

            @Override
            public String toString() {
                return "CommentsBean{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", url='" + url + '\'' +
                        ", date='" + date + '\'' +
                        ", content='" + content + '\'' +
                        ", parent=" + parent +
                        '}';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getParent() {
                return parent;
            }

            public void setParent(int parent) {
                this.parent = parent;
            }
        }
    }
}
