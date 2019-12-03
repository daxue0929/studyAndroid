package com.daxue.studyandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.daxue.studyandroid.utils.OkHttpUtils;
import com.daxue.studyandroid.utils.callback.OkHttpCallback;
import com.daxue.studyandroid.views.DrawView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

@Route(path = MyARouter.TestTActivity)
public class TestTActivity extends Activity {
//    private final String TAG = TestTActivity.class.getSimpleName();
    private final String TAG = "TestTActivity";

    private OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        LinearLayout circle = findViewById(R.id.red_circle);
        DrawView drawView = new DrawView(this);
        //设置自定义组件的最大宽度高度
        drawView.setMinimumWidth(300);
        drawView.setMinimumHeight(450);



        //为draw组件绑定Touch事件
        drawView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //修改draw组件的currentX 和currentY坐标
                drawView.currentX = event.getX();
                drawView.currentY = event.getY();
                //通知draw组件重绘
                drawView.invalidate();
                return true;
            }
        });
        circle.addView(drawView);



//        ARouter.getInstance().inject(this);
        Intent loadIntent = getIntent();
        String loadTest = loadIntent.getStringExtra("test");
        Log.i(TAG, loadTest);


    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
        // 某个键被按下触发方法
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
        // 松开某个键触发的方法
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
    }

    public void http() {
        okHttpUtils.get("",new OkHttpCallback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                super.onResponse(call, response);
                Log.i(TAG, result);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                super.onFailure(call, e);

            }

            @Override
            public void onFinish(String status, String msg) {
                super.onFinish(status, msg);

            }
        });

    }
}

