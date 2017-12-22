package com.shop.newshop_application.http.result.taoutiaoresult;

import java.io.Serializable;

/**
 * Created by u on 2017/12/20.
 */

public class MineShopSumListInfo implements Serializable {

    private String shopimgurl;
    private String shopname;
    private int shopsum;
    private  int shopprice;
    private  int shopallprice;

    public MineShopSumListInfo(String shopimgurl, String shopname, int shopsum, int shopprice) {
        this.shopimgurl = shopimgurl;
        this.shopname = shopname;
        this.shopsum = shopsum;
        this.shopprice = shopprice;
        this.shopallprice=shopsum*shopprice;
    }

    public int getShopprice() {
        return shopprice;
    }

    public void setShopprice(int shopprice) {
        this.shopprice = shopprice;
    }

    public int getShopallprice() {
        if (this.shopsum!=0){
            int a=this.shopprice*this.shopsum;
            return a;
        }
        return shopprice;
    }

    public void setShopallprice(int shopallprice) {
        this.shopallprice=shopallprice ;
    }

    public String getShopimgurl() {
        return shopimgurl;
    }

    public void setShopimgurl(String shopimgurl) {
        this.shopimgurl = shopimgurl;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public int getShopsum() {
        return shopsum;
    }

    public void setShopsum(int shopsum) {
        this.shopsum = shopsum;
    }

    @Override
    public String toString() {
        return "MineShopSumListInfo{" +
                "shopimgurl='" + shopimgurl + '\'' +
                ", shopname='" + shopname + '\'' +
                ", shopsum=" + shopsum +
                '}';
    }
}
