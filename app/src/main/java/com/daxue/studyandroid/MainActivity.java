package com.daxue.studyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
public class MainActivity extends AppCompatActivity {
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

        findViewById(R.id.first_btn).setOnClickListener(v -> {
            // 发起路由操作，替换intent意图跳转
//            ARouter.getInstance().inject(this);


            ARouter.getInstance().build(MyARouter.TestTActivity)
                    .withString("test","Test Demo String")
                    .navigation();

//            startActivity(new Intent(MainActivity.this, TestTActivity.class));

        });
    }



    public void autoInjectAllField(Activity activity) throws IllegalAccessException, IllegalArgumentException {
        //得到Activity对应的Class
        Class clazz = this.getClass();
        //得到该Activity的所有字段
        Field[] fields = clazz.getDeclaredFields();
        Log.v(TAG, "fields size-->" + fields.length);
        for (Field field : fields) {
            //判断字段是否标注InjectView
            if (field.isAnnotationPresent(ViewById.class)) {
                Log.v(TAG, "is injectView");
                //如果标注了，就获得它的id
                ViewById inject = field.getAnnotation(ViewById.class);
                int id = inject.id();
                Log.v(TAG, "id--->" + id);
                if (id > 0) {
                    //反射访问私有成员，必须加上这句
                    field.setAccessible(true);
                    //然后对这个属性复制
                    field.set(activity, activity.findViewById(id));
                }
            }
        }
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
