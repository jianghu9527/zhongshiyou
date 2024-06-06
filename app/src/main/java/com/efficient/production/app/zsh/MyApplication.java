package com.efficient.production.app.zsh;

import android.app.Application;

import com.bigemap.bmcore.BMEngine;


public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        BMEngine.preInit(this, "af655b5079055e38f095313c27a84345");
//        BMEngine.init(this, this. getFilesDir().getPath(), false);

    }
    public static MyApplication getInstance() {
        return instance;
    }



}
