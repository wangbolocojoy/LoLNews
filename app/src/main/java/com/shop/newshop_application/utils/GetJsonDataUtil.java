package com.shop.newshop_application.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 王波 on 2017/8/12 0012.
 */

public class GetJsonDataUtil {
    public static GetJsonDataUtil intance=null;
    public static GetJsonDataUtil getIntance(){
        if (intance==null){
            intance=new GetJsonDataUtil();
        }
        return intance;
    }

    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
