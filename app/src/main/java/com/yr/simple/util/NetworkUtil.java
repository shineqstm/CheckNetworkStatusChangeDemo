package com.yr.simple.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yr.simple.listener.CheckNetworkStatusChangeListener;

/**
 * @ Author yemao
 * @ Email yrmao9893@163.com
 * @ Date 2018/7/31
 * @ Des
 */
public class NetworkUtil {
    /**
     * 获取当前网络类型 CheckNetworkStatusChangeListener.Status
     *
     * @return 返回网络类型 CheckNetworkStatusChangeListener.Status
     */
    public static CheckNetworkStatusChangeListener.Status getNetworkConnectionType(Context context) {
        //获取连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return CheckNetworkStatusChangeListener.Status.TYPE_UN_NETWORK;
        //获取网络连接信息
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return CheckNetworkStatusChangeListener.Status.TYPE_WIFI;
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return CheckNetworkStatusChangeListener.Status.TYPE_MOBILE;
            }
        }
        return CheckNetworkStatusChangeListener.Status.TYPE_UN_NETWORK;
    }
}
