package com.daxue.studyandroid.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class BaseActiviyt extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private HashMap getStrsFromPrev() {
        HashMap<String, Object> map = new HashMap<>();
        Intent prevIntent = getIntent();
        String dataString = prevIntent.getDataString();

        return map;
    }
}
