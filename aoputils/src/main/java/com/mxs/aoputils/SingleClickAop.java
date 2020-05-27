package com.mxs.aoputils;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Calendar;

/**
 * @author Fs972
 * @date 2020/5/19 3:07 PM
 * @description 防重复点击的Aop
 */

@Aspect
public class SingleClickAop {

    private static final String TAG = "SingleClickAop";

    /**
     * 设置切点
     */
    @Pointcut("execution(@com.mxs.aoputils.SingleClick * *(..))")
    public void executionSingleClick() {

    }

    @Around("executionSingleClick()")
    public void executionSingleClickAround(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        SingleClick singleClick = methodSignature.getMethod().getAnnotation(SingleClick.class);
        if (singleClick == null){
            return;
        }
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            if (obj instanceof View) {
                view = (View) obj;
            }
        }
        Log.e(TAG, "executionSingleClickAround: "+"1111" );
        if (view != null) {
            Object tag = view.getTag(view.getId());
            long lastClickTime = tag == null ? 0 : (long) tag;
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime >= singleClick.clickIntervals()) {
                view.setTag(view.getId(), currentTime);
                joinPoint.proceed();
            }
        }
    }
}
