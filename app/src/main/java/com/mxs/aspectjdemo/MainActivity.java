package com.mxs.aspectjdemo;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mxs.aoputils.MethodTime;
import com.mxs.aoputils.SingleClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_onclick1).setOnClickListener(this);
        findViewById(R.id.tv_onclick2).setOnClickListener(this);

    }

    @MethodTime
    private void setMedia() {
        SystemClock.sleep(3000);
    }

    @SingleClick(clickIntervals = 3000)
    public void firstOnClick(View view) {
        Log.e(TAG, "firstOnClick: ");
//        setMedia();
    }

    @SingleClick(clickIntervals = 3000)
    public void twoOnClick(View view) {
        Log.e(TAG, "twoOnClick: ");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_onclick1:
                firstOnClick(view);
                break;
            case R.id.tv_onclick2:
                twoOnClick(view);
                break;
            default:
        }
    }
}
