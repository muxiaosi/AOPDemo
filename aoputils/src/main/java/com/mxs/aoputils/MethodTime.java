package com.mxs.aoputils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Fs972
 * @date 2020/5/19 11:47 AM
 * @description 统计方法时间注解
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface MethodTime {

}
