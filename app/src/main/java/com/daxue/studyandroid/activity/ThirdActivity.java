package com.daxue.studyandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.daxue.studyandroid.R;

/**
 * 这是第三个活动
 */
public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);




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

        UI ui = new UI(ThirdActivity.this);

        View viewById = findViewById(R.id.unicomsdk_logo_img);

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
