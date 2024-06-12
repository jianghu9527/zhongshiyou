package cn.sichuan.cd.zzsy.zsh;

import cn.sichuan.cd.common.BaseCommonApplication;


public class MyApplication extends BaseCommonApplication {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


    }
    public static MyApplication getInstance() {
        return instance;
    }



}
