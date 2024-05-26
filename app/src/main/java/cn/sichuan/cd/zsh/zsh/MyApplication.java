package cn.sichuan.cd.zsh.zsh;

import android.app.Application;


public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


    }
}
