package cn.sichuan.cd.common

import android.app.Application
import android.util.Log

open class BaseCommonApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Log.d("---------------------","-------------BaseApplication------Common--");
    }
}