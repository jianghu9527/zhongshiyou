package cn.sichuan.cd.zsh.ui

import cn.sichuan.cd.zsh.BuildConfig
import com.blankj.utilcode.util.LogUtils
import cn.sichuan.cd.zsh.model.app.base.BaseApplication

import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class MyApplication : BaseApplication() {

    companion object {
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
                ClassicsHeader(context)
            }
        }

       lateinit var   instan:MyApplication;
    }


    override fun onCreate() {
        super.onCreate()
        instan=this;
        LogUtils.getConfig().run {
            isLogSwitch = BuildConfig.DEBUG
            setSingleTagSwitch(true)
        }
    }
}