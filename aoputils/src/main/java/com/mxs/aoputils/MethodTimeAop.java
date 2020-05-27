package com.mxs.aoputils;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

/**
 * @author Fs972
 * @date 2020/5/19 11:51 AM
 * @description TODO
 */

@Aspect
public class MethodTimeAop {

    private static final String TAG = "MethodTimeAop";

    @Pointcut("execution(@com.mxs.aoputils.MethodTime * *(..))")
    public void executionMethodTime() {

    }

    @Around("executionMethodTime()")
    public void executionMethodTimeAround(ProceedingJoinPoint joinPoint) {
        long startTime = Calendar.getInstance().getTimeInMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = Calendar.getInstance().getTimeInMillis();
        Log.e(TAG, "executionMethodTimeBefore: " + (end - startTime));
    }
}
