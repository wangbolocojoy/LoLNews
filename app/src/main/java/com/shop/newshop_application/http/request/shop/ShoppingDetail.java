package com.shop.newshop_application.http.request.shop;

import com.shop.newshop_application.http.ParamField;
import com.shop.newshop_application.http.request.HttpParams;

/**
 * Created by MacBookPor on 2018/1/10.
 */

public class ShoppingDetail implements HttpParams {
    @ParamField("act")
    private String act;
    @ParamField("op")
    private String op;
    @ParamField("goods_id")
    private String goods_id;



    public ShoppingDetail(String goods_id) {
        this.goods_id = goods_id;
        this.act="goods";
        this.op="goods_detail";
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }


}
