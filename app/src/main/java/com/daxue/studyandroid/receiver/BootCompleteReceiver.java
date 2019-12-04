package com.daxue.studyandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 *
 * 不要在 onReceive()方法中添加过多的逻辑或者进行任何的耗时操作，因为在广播接收
 * 器中是不允许开启线程的，当 onReceive()方法运行了较长时间而没有结束时，程序就会报错。
 *
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_LONG).show();

        /**
         * 这一行代码表示将接受到的这条广播截断
         */
//        abortBroadcast();
    }


}
