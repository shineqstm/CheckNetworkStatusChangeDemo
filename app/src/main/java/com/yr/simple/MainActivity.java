package com.yr.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("我是首页");
//        setCheckNetworkStatusChangeListenerEnable(true);
    }
}
