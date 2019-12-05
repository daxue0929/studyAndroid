package com.daxue.studyandroid.utils;

import android.content.Context;
import android.util.Log;

import com.daxue.studyandroid.activity.ActivityCollector;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
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
}
