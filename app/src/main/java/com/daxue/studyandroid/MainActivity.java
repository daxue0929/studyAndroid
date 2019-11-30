package com.daxue.studyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.daxue.studyandroid.utils.OkHttpUtils;
import com.daxue.studyandroid.utils.callback.OkHttpCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        okHttpUtils.get("http://122.51.170.63:8080/", new OkHttpCallback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                super.onResponse(call, response);

                Log.i(TAG, result);
            }
        });
    }
}
