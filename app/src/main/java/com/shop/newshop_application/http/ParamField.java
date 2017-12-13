package com.shop.newshop_application.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wb
 * on 2017/11/22 
 * on ParamField 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ParamField {
	// 服务器交互字段名
	String value() default "";
}
