package com.daxue.studyandroid.config;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.daxue.studyandroid.BuildConfig;

/**
 * 全局初始化类需要在AndroidMainFest.xml中指定 application 的 name 为本类
 */
public class App extends Application {

    private static final String TAG = "App";

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        if (BuildConfig.DEBUG) { //这两行必须写在init之前，否则这些配置在init中将要无效
            ARouter.openLog(); //打印日志
            ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行必须开启调试模式！线上版本需要关闭，否则有安全隐患)
        }
        ARouter.init(this); //尽可能早推荐在Application中初始化
    }


}


