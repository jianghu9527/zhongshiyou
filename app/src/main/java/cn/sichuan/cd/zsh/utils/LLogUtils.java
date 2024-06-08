package cn.sichuan.cd.zsh.utils;

import android.util.Log;

import cn.sichuan.cd.zsh.BuildConfig;

/**
 * @author L
 * @time 2020/7/1d
 * @describe $
 */
public class LLogUtils {
    public static final String TAG = "----------日志输出------------";

    public static void log(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content);
        }
    }

    public static void log(String content) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, content);
        }
    }

    public static void d(String content) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, content);
        }
    }

    public static void e(String content) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, content);
        }
    }

    /**
     * 分段打印出较长log文本
     *
     * @param log       原log文本
     * @param showCount 规定每段显示的长度（最好不要超过eclipse限制长度）
     */
    public static void showLogCompletion(String log, int showCount) {
        try{
            if (log.length() > showCount) {
                String show = log.substring(0, showCount);
                Log.d(TAG, show + "");
                if ((log.length() - showCount) > showCount) {//剩下的文本还是大于规定长度
                    String partLog = log.substring(showCount, log.length());
                    showLogCompletion(partLog, showCount);
                } else {
                    String surplusLog = log.substring(showCount, log.length());
                    Log.d(TAG, surplusLog + "");
                }
            } else {
                Log.d(TAG, log + "");
            }
        }catch (OutOfMemoryError e){
            Log.d(TAG, log + "日志过长，不能打印了");
        }

    }


}