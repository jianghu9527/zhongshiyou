package cn.sichuan.cd.zzsy.ui.utils

import cn.sichuan.cd.zzsy.ui.http.HomeNetWork
import cn.sichuan.cd.zzsy.ui.http.HomeRepository


object InjectorUtil {

    fun getHomeRepository() = HomeRepository.getInstance(HomeNetWork.getInstance())

}