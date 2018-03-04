package com.blog.news.utils.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.rhino.ui.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>json工具类</p>
 *
 */
public class JsonHelper {

	/**
	 * 将返回的json字符串转出对象
	 * @param json json数据
	 * @param clazz 对象类
	 * @return 目标对象
	 */
	public static <T> T formatObj(String json, Class<T> clazz) {
		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			Log.e(e);
		}
		return null;
	}
	
	/**
	 * 将返回的json字符串转出对象
	 * @param json json数据
	 * @param clazz 对象类
	 * @return 目标对象
	 */
	public static <T> List<T> formatArray(String json, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			JSONArray jsonArray = JSON.parseArray(json);
			if(null == jsonArray){
				Log.e("the JSONArray is null, json: " + json);
				return null;
			}
			for(int i = 0; i < jsonArray.size(); i++){
				T data = formatObj(jsonArray.getString(i), clazz);
				list.add(data);
			}
			return list;
		} catch (Exception e) {
			Log.e(e);
		}
		return null;
	}
	public static String upStringData(String Data){
		StringBuffer b=new StringBuffer();
		Matcher m= Pattern.compile("\\\\u([0-9a-fA-F]{4})").matcher(Data);
		while (m.find()){

		}
		b.append((char)Integer.parseInt(m.group(1),16));
		return b.toString();

	}
}
