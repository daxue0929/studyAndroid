package com.daxue.studyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.daxue.studyandroid.config.ViewById;
import com.daxue.studyandroid.utils.OkHttpUtils;
import com.daxue.studyandroid.utils.callback.OkHttpCallback;

import java.io.IOException;
import java.lang.reflect.Field;


import okhttp3.Call;
import okhttp3.Response;

@Route(path = MyARouter.MainActivity)
public class MainActivity extends AppCompatActivity implements LifecycleObserver {
    private final String TAG = MainActivity.class.getSimpleName();

    private OkHttpUtils okHttpUtils = OkHttpUtils.getInstance();

    @ViewById(id = R.id.view1)
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            autoInjectAllField(this);
        } catch (IllegalAccessException e) {
            Log.i(TAG, e.getMessage());
        }

        initClick();


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.unicomsdk_logo, "this is ticker text", System.currentTimeMillis());

//        notification.setLatestEventInfo(MainActivity.this, "This is content title", "This is content text", null);
        manager.notify(1, notification);

    }

    private void initClick() {
        findViewById(R.id.first_btn).setOnClickListener(v -> {
            // 发起路由操作，替换intent意图跳转
//            ARouter.getInstance().inject(this);
            ARouter.getInstance().build(MyARouter.TestTActivity)
                    .withString("test","Test Demo String")
                    .navigation();
//            startActivity(new Intent(MainActivity.this, TestTActivity.class));
        });

        findViewById(R.id.second_btn).setOnClickListener(v -> {
            ARouter.getInstance().build(MyARouter.DataBaseActivity)
                    .withString("str","SQLite数据库测试页面")
                    .navigation();
        });

    }


    public void autoInjectAllField(Activity activity) throws IllegalAccessException, IllegalArgumentException {
        //得到Activity对应的Class
        Class clazz = this.getClass();
        //得到该Activity的所有字段
        Field[] fields = clazz.getDeclaredFields();
        Log.v(TAG, "fields size-->" + fields.length);
        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewById.class)) {
                Log.v(TAG, "is injectView");
                ViewById inject = field.getAnnotation(ViewById.class);
                int id = inject.id();
                Log.v(TAG, "id--->" + id);
                if (id > 0) {
                    field.setAccessible(true);
                    field.set(activity, activity.findViewById(id));
                }
            }
        }
    }




    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();


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

    public native String testString();

}
