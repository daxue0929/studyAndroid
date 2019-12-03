package com.daxue.studyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.daxue.studyandroid.utils.OkHttpUtils;
import com.daxue.studyandroid.utils.callback.OkHttpCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

@Route(path = MyARouter.MainActivity)
public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.first_btn).setOnClickListener(v -> {
            // 发起路由操作，替换intent意图跳转
//            ARouter.getInstance().inject(this);


            ARouter.getInstance().build(MyARouter.TestTActivity)
                    .withString("test","Test Demo String")
                    .navigation();

//            startActivity(new Intent(MainActivity.this, TestTActivity.class));

        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    public void test() {

        okHttpUtils.get("http://122.51.170.63:8080/", new OkHttpCallback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                super.onResponse(call, response);

                Log.i(TAG, result);
            }
        });
    }
}
