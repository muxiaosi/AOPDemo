package com.mxs.aoputils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Fs972
 * @date 2020/5/19 3:03 PM
 * @description 仿重复点击
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleClick {
    /**
     * 设置防重复点击时间
     * @return 防重复点击时间，若没有设置默认为1s
     */
    long clickIntervals() default 1000;
}
