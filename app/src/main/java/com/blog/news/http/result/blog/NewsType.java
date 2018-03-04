package com.blog.news.http.result.blog;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MacBookPor on 2018/2/23.
 */

public class NewsType implements Serializable{


    /**
     * status : ok
     * count : 5
     * categories : [{"id":2,"slug":"tuijian","title":"推荐","description":"","parent":0,"post_count":2},{"id":3,"slug":"hot","title":"热点","description":"","parent":0,"post_count":4},{"id":5,"slug":"shehui","title":"社会","description":"","parent":0,"post_count":4},{"id":6,"slug":"keji","title":"科技","description":"","parent":0,"post_count":3},{"id":11,"slug":"zhuanzai","title":"转载","description":"","parent":0,"post_count":1}]
     */

    private String status;
    private int count;
    private List<CategoriesBean> categories;

    @Override
    public String toString() {
        return "NewsType{" +
                "status='" + status + '\'' +
                ", count=" + count +
                ", categories=" + categories +
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

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public static class CategoriesBean {
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
}
