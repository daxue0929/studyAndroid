package com.daxue.studyandroid.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 安卓存储之文件存储
 *      存储较为简单的数据
 */
public class FileUtil {
    private static final String TAG = "FileUtil";
    /**
     * 安卓文件存储
     * 默认文件追加模式
     * @param context
     * @param fileName
     */
    public void save(Context context, String fileName, String data) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput(fileName, Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);

        } catch (IOException e) {
            Log.w(TAG, e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    Log.w(TAG, e.getMessage());
                }
            }
        }

    }

    /**
     * 键值对的存储方式，可存储的类型为 String boolean long int float
     * @param context
     * @param fileName
     * @param key
     * @param value
     */
    public void setValue(Context context, String fileName, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public String getValue(Context context, String fileName, String key) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String result = preferences.getString(key, "");
        return result;
    }



    public void test(Context context,String dataBaseName) {
        ContentValues views = new ContentValues();
        views.put("id", "");
//        ....
        saveDataBase(context,dataBaseName, views);

    }

    /**
     * 数据库存储
     * sqLite
     */
    public void saveDataBase(Context context, String dataBaseName, ContentValues columnViews) {
        MyDataBaseHelper sqLite = new MyDataBaseHelper(context, dataBaseName, null, 1);
//        SQLiteDatabase database = sqLite.getWritableDatabase();  //于创建和升级数据库的，不仅如此，这两个方法还都会返回一个SQLiteDatabase//对象，借助这个对象就可以对数据进行 CRUD 操作了
        SQLiteDatabase database = sqLite.getReadableDatabase();
        database.insert("yableda", null, columnViews);


    }
}

class MyDataBaseHelper extends SQLiteOpenHelper {
    public static final String TAG = "MyDataBaseHelper";

    public static final String CREATE_BOOK = "create table book (id integer primary key autoincrement, author text, price real, pages integer, name text)";

    Context mContext;

    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Log.i(TAG, "Create succeeded");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}