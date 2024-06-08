package cn.sichuan.cd.zsh.model.app.base

import android.app.Application
import android.content.Context


open class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}