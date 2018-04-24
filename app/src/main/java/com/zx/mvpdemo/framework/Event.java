package com.zx.mvpdemo.framework;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//动态指定事件
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Event {

    int[] value();

    Class<?> type() default View.OnClickListener.class;

    String setter() default "";

    String method() default "";
}
