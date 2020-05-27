package com.mxs.aspectjdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Fs972
 * @date 2020/5/22 3:44 PM
 * @description TODO
 */
public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    Handler mHandleMessageHandler;

    /**
     * 发送消息
     */
    public void sendMessage(View view) {
        //创建线程handleMessage，接受处理message
        Thread handleMessageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Looper初始化准备
                Looper.prepare();

                mHandleMessageHandler = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        System.out.println("I am handleMessage start handler Message");
                        System.out.println(msg.obj);
                        System.out.println("I am "+mHandleMessageHandler.getLooper().toString());
                    }
                };
                System.out.println("I am :" + mHandleMessageHandler.getLooper());
                //Looper循环
                Looper.loop();
            }
        });

        //创建线程sendMessageThread，发送message
        Thread sendMessageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am sendMessageThread send Message");
                SystemClock.sleep(3000);
                Message message = Message.obtain();
                message.obj = "I am sendMessageThread Message";
                mHandleMessageHandler.sendMessage(message);
            }
        });
        handleMessageThread.start();
        sendMessageThread.start();
    }
}
