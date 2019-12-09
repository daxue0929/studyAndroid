package com.daxue.studyandroid.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.daxue.studyandroid.MyARouter;
import com.daxue.studyandroid.R;

import java.net.URL;

@Route(path = MyARouter.DataBaseActivity)
public class DataBaseActivity extends AppCompatActivity {

    private AppCompatActivity mContext;

    private ContentResolver contentResolver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        mContext = DataBaseActivity.this;

        contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://com.example.app.provider/table1");
//        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);

    }

    @Override
    protected void onStart() {
        super.onStart();

        initDataBase();
    }

    private void initDataBase() {
        final String CREATE_TABLE = "create table book (" +
                "id integer primary key autoincrement, " +
                "author text, " +
                "price real, " +
                "pages integer, " +
                "name text)";

    }
}
