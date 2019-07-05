package com.yr.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.yr.simple.listener.CheckNetworkStatusChangeListener;
import com.yr.simple.receiver.CheckNetworkStatusChangeReceiver;

import java.lang.ref.WeakReference;

/**
 * @ Author yemao
 * @ Email yrmao9893@163.com
 * @ Date 2018/7/31
 * @ Des自定义Handler，使用弱引用防止内存溢出
 */
public class SimpleHandler<T extends Activity> extends Handler {
    WeakReference<T> weakReference;

    public SimpleHandler(T t) {
        this.weakReference = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (weakReference.get() != null) {
            //发送广播
            Intent mCheckNetworkIntent = new Intent();
            mCheckNetworkIntent.setAction(CheckNetworkStatusChangeReceiver.ACTION);
            CheckNetworkStatusChangeListener.Status status= (CheckNetworkStatusChangeListener.Status) msg.obj;
            mCheckNetworkIntent.putExtra(CheckNetworkStatusChangeReceiver.EVENT,status);
            weakReference.get().sendBroadcast(mCheckNetworkIntent);
        }
    }

}
