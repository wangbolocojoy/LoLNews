package com.blog.news.http.result.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by u on 2017/12/20.
 */

public class MineShopSumListInfo implements Serializable {


    /**
     * total : 3096
     * data : [{"id":536563,"title":"new2 - 阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待","sellPoint":"清仓！仅北京，武汉仓有货！","price":29900000,"image":"http://image.taotao.com/jd/4ef8861cf6854de9889f3db9b24dc371.jpg"},{"id":562379,"title":"new8- 三星 W999 黑色 电信3G手机 双卡双待双通","sellPoint":"下单送12000毫安移动电源！双3.5英寸魔焕炫屏，以非凡视野纵观天下时局，尊崇翻盖设计，张弛中，尽显从容气度！","price":1100,"image":"http://image.taotao.com/jd/d2ac340e728d4c6181e763e772a9944a.jpg"},{"id":605616,"title":"阿尔卡特 (OT-979) 冰川白 联通3G手机","sellPoint":"清仓！仅上海仓有货！","price":30900,"image":"http://image.taotao.com/jd/a69d0d09a1a04164969c2d0369659b1a.jpg"},{"id":635906,"title":"阿尔卡特 (OT-927) 单电版 炭黑 联通3G手机 双卡双待","sellPoint":"清仓！仅北京，武汉仓有货！","price":24900,"image":"http://image.taotao.com/jd/9c1fcdf2bf20450788195c707da00a87.jpg"},{"id":679532,"title":"阿尔卡特 (OT-986+) 玫红 AK47 加强版 联通3G手机","sellPoint":"仅上海，广州，沈阳仓有货！预购从速！","price":49900,"image":"http://image.taotao.com/jd/65e2007d41dc4e3cb308833a1a910f8d.jpg"},{"id":679533,"title":"阿尔卡特 (OT-986+) 曜石黑 AK47 加强版 联通3G手机","sellPoint":"少量库存，抢完即止！<a  target=\"blank\"  href=\"http://sale.jd.com/act/bxYeI1346g.html?erpad_source=erpad\">\u201c领券更优惠！\u201d<\/a>","price":49900,"image":"http://image.taotao.com/jd/b3251c85da8e4302b7389f3371dd0a68.jpg"},{"id":691300,"title":"三星 B9120 钛灰色 联通3G手机 双卡双待双通","sellPoint":"下单即送10400毫安移动电源！再赠手机魔法盒！","price":439900,"image":"http://image.taotao.com/jd/c1775819c7e44b1c903f27514e70b998.jpg"},{"id":738388,"title":"三星 Note II (N7100) 云石白 联通3G手机","sellPoint":"经典回顾！超值价格值得拥有。","price":169900,"image":"http://image.taotao.com/jd/089b79cbe19f454dab24cce65f2e9602.jpg"},{"id":741524,"title":"三星 Note II (N7100) 钛金灰 联通3G手机","sellPoint":"下单赠12000毫安移动电源","price":169900,"image":"http://image.taotao.com/jd/29e1b92dc7e146489ce46a2262479a0f.jpg"},{"id":816448,"title":"三星 Note II (N7100) 钻石粉 联通3G手机","sellPoint":"经典回顾！超值特惠！","price":169900,"image":"http://image.taotao.com/jd/5a45e88aeca046ec88d7b7ffbc47092a.jpg"}]
     */

    private int total;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 536563
         * title : new2 - 阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待
         * sellPoint : 清仓！仅北京，武汉仓有货！
         * price : 29900000
         * image : http://image.taotao.com/jd/4ef8861cf6854de9889f3db9b24dc371.jpg
         */

        private int id;
        private String title;
        private String sellPoint;
        private int price;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSellPoint() {
            return sellPoint;
        }

        public void setSellPoint(String sellPoint) {
            this.sellPoint = sellPoint;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", sellPoint='" + sellPoint + '\'' +
                    ", price=" + price +
                    ", image='" + image + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MineShopSumListInfo{" +
                "total=" + total +
                ", data=" + data +
                '}';
    }
}
