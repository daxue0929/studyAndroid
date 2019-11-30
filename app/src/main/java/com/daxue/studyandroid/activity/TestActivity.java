package com.daxue.studyandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.daxue.studyandroid.R;
import com.daxue.studyandroid.utils.OkHttpUtils;
import com.daxue.studyandroid.utils.callback.OkHttpCallback;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class TestActivity extends Activity {
    private final String TAG = TestActivity.class.getSimpleName();

    private OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


    }


    @Override
    protected void onStart() {
        super.onStart();

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
