package com.yr.simple.listener;

/**
 * @ Author yemao
 * @ Email yrmao9893@163.com
 * @ Date 2018/7/31
 * @ Des 网络变化监听
 */
public interface CheckNetworkStatusChangeListener {
    /*
      网络变化会调用
     */
    void onEvent(Status status);

    /**
     * 网络状态
     * TYPE_UN_NETWORK 沒有网络
     * TYPE_WIFI WiFi连接
     * TYPE_MOBILE 移动数据
     */
    enum Status {
        TYPE_UN_NETWORK,
        TYPE_WIFI,
        TYPE_MOBILE,
    }
}
