package com.yr.simple.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.yr.simple.R;

/**
 * @ Author yemao
 * @ Email yrmao9893@163.com
 * @ Date 2018/7/31
 * @ Des
 */
public class NetworkStatusLayout extends LinearLayout {
    public NetworkStatusLayout(Context context) {
        super(context);
        inflate(context, R.layout.layout_network_status, this);
    }
}
