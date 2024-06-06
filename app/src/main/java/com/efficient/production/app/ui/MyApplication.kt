package com.efficient.production.app.ui

import com.efficient.production.app.BuildConfig
import com.blankj.utilcode.util.LogUtils
import com.efficient.production.app.model.app.base.BaseApplication

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