package cn.sichuan.cd.zsh.ui

import cn.sichuan.cd.zsh.BuildConfig
import cn.sichuan.cd.zsh.model.base.BaseApplication
import com.blankj.utilcode.util.LogUtils

import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class MyApplication : BaseApplication() {

    companion object {
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
                ClassicsHeader(context)
            }
        }
    }


    override fun onCreate() {
        super.onCreate()

        LogUtils.getConfig().run {
            isLogSwitch = BuildConfig.DEBUG
            setSingleTagSwitch(true)
        }
    }
}