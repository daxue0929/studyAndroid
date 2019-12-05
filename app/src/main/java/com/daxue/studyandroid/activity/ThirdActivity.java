package com.daxue.studyandroid.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.daxue.studyandroid.MyARouter;
import com.daxue.studyandroid.R;
import com.daxue.studyandroid.utils.FileUtil;

/**
 * 这是第三个活动
 */
@Route(path = MyARouter.ThirdActivity)
public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivity";

    private IntentFilter intentFilter;



    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        View login = findViewById(R.id.unicomsdk_login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.daxue.studyandroid.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent); //发送本地广播
            }
        });

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.daxue.studyandroid.LOCAL_BROADCAST");
        localBroadcastManager.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                /**
                 * 本地广播接收
                 * 本地广播无法通过静态注册的方式来接收
                 */

            }
        }, intentFilter);




        //为当前页面设置一个头部
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable drawable = new ColorDrawable(0xff74cb26);
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setBackgroundDrawable(drawable);
            actionBar.setHomeAsUpIndicator(R.drawable.unicom_return_bg);

            actionBar.setTitle("登录成功页");
        }
//        只接收一个参数，默认把当前活动的类名作为SharedPreferences的文件名字
//        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        UI ui = new UI(ThirdActivity.this);

        View viewById = findViewById(R.id.unicomsdk_logo_img);

        Log.w(TAG, "页面渲染完毕");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:  //点击返回图标事件
                this.finish();
                break;
            default:
                Log.i(TAG, "onOptionsItemSelected method default match in the switch");
        }


        return super.onOptionsItemSelected(item);
    }

    public void goNext(View view) {
        ARouter.getInstance().build(MyARouter.FourthActivity).navigation();

        FileUtil fileUtil = new FileUtil();

        String str = "你好啊，新的小伙伴";
        if (!TextUtils.isEmpty(str)) {
            fileUtil.save(ThirdActivity.this, "fileName",str);
        }

    }
}


class UI {

    public UI(ThirdActivity context){
        View logo_img = context.findViewById(R.id.unicomsdk_logo_img);  // logo图片可替换 | logo大小位置可修改
        TextView unicomsdk_mobile_number = context.findViewById(R.id.unicomsdk_mobile_number);  //文字大小颜色位置可修改
        TextView unicomsdk_identify_tv = context.findViewById(R.id.unicomsdk_identify_tv);  //文字大小颜色可修改
        TextView unicomsdk_quick_login_text = context.findViewById(R.id.unicomsdk_quick_login_text);
        ImageView unicomsdk_btn_loading_animation = context.findViewById(R.id.unicomsdk_btn_loading_animation);
        TextView unicomsdk_switch_account = context.findViewById(R.id.unicomsdk_switch_account); //字体颜色可修改   切换账号功能可隐藏

    }
    public UI(int var1) { }
}
