package com.yr.simple.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yr.simple.listener.CheckNetworkStatusChangeListener;

/**
 * @ Author yemao
 * @ Email yrmao9893@163.com
 * @ Date 2018/7/31
 * @ Des 监听网络变化的广播
 */
public class CheckNetworkStatusChangeReceiver extends BroadcastReceiver {
    public static final String EVENT = "event";
    public static final String ACTION="action";
    private CheckNetworkStatusChangeListener mCheckNetworkStatusChangeListener;

    public CheckNetworkStatusChangeReceiver() {

    }

    public void setCheckNetworkStatusChangeListener(CheckNetworkStatusChangeListener mCheckNetworkStatusChangeListener) {
        this.mCheckNetworkStatusChangeListener = mCheckNetworkStatusChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        CheckNetworkStatusChangeListener.Status mStatus = (CheckNetworkStatusChangeListener.Status) intent.getSerializableExtra(EVENT);
        mCheckNetworkStatusChangeListener.onEvent(mStatus);
    }
}
