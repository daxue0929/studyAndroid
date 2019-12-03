package com.daxue.studyandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daxue.studyandroid.R;

import java.util.Timer;
import java.util.TimerTask;

public class FrameLayoutTest extends Activity {

    private int currentColor = 0;

    //定义一个颜色组
    final int[] colors = new int[]{
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4
    };

    final int[] names = new int[]{
            R.id.view0,
            R.id.view1,
            R.id.view2,
            R.id.view3

    };

    TextView[] views = new TextView[4];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        for (int i = 0; i<4; i++) {
            views[i] = findViewById(names[i]);
        }
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x1122) {
                    //依次改变4个TextView的背景色
                    for (int i=0; i < 4- currentColor; i++){
                        views[i].setBackgroundResource(colors[i+currentColor]);
                    }
                    for (int i = 4-currentColor,j=0; i<4;i++,j++ ){
                        views[i].setBackgroundResource(colors[j]);
                    }
                }
                super.handleMessage(msg);

            }
        };


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                currentColor ++;
                if (currentColor >=3){
                    currentColor = 0;
                }
                //发送一条消息通知系统改变4个TextView组件的背景色
                Message m = new Message();
                //给该消息定义一个标识
                m.what = 0x1122;
                handler.sendMessage(m);
            }
        }, 0, 100);

    }
}
