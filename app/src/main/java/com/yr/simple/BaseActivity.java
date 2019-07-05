package com.yr.simple;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yr.simple.listener.CheckNetworkStatusChangeListener;
import com.yr.simple.receiver.CheckNetworkStatusChangeReceiver;
import com.yr.simple.util.NetworkUtil;
import com.yr.simple.widget.NetworkStatusLayout;

/**
 * @ Author YeMao
 * @ Email yrmao9893@163.com
 * @ Date 2018/7/31
 * @ Des Activity 基类
 */
public class BaseActivity extends AppCompatActivity implements CheckNetworkStatusChangeListener {
    private CheckNetworkStatusChangeReceiver mCheckNetworkStatusChangeReceiver;
    private String LOG = BaseActivity.class.getSimpleName();
    private SimpleHandler<BaseActivity> simpleHandler;
    private NetworkStatusLayout mNetworkStatusLayout;
    private boolean checkNetworkStatusChangeListenerEnable=true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        init();
        new Thread(mRunnable).start();
    }

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout mRootLayout = findViewById(R.id.root_linear_layout);
        //将网络状态view添加到根视图
        mNetworkStatusLayout = new NetworkStatusLayout(this);
        //默认隐藏状态
        mNetworkStatusLayout.setVisibility(View.GONE);
        mRootLayout.addView(mNetworkStatusLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //将子类的layout，添加到根目录
        View mContentView = LayoutInflater.from(this).inflate(layoutResID, null);
        mRootLayout.addView(mContentView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void init() {
        mCheckNetworkStatusChangeReceiver = new CheckNetworkStatusChangeReceiver();
        mCheckNetworkStatusChangeReceiver.setCheckNetworkStatusChangeListener(this);
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(CheckNetworkStatusChangeReceiver.ACTION);
        registerReceiver(mCheckNetworkStatusChangeReceiver, mIntentFilter);
        simpleHandler = new SimpleHandler<BaseActivity>(this);
    }

    public void setCheckNetworkStatusChangeListenerEnable(boolean checkNetworkStatusChangeListener) {
        this.checkNetworkStatusChangeListenerEnable = checkNetworkStatusChangeListener;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            //实现每隔一秒检测一次网络
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CheckNetworkStatusChangeListener.Status status = NetworkUtil.getNetworkConnectionType(BaseActivity.this);
                Message message = new Message();
                message.obj = status;
                simpleHandler.sendMessage(message);
            }
        }
    };


    @Override
    public void onEvent(Status status) {
        Log.w(LOG, "status: " + status.name());
        if (!checkNetworkStatusChangeListenerEnable)
            return;
        if (status == Status.TYPE_UN_NETWORK) {
            if (mNetworkStatusLayout.getVisibility() == View.GONE)
                mNetworkStatusLayout.setVisibility(View.VISIBLE);
        } else {
            if (mNetworkStatusLayout.getVisibility() == View.VISIBLE)
                mNetworkStatusLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mCheckNetworkStatusChangeReceiver);
        simpleHandler.removeCallbacks(mRunnable);
    }
}
