package cn.sichuan.cd.zsh.ui.utils

import cn.sichuan.cd.zsh.ui.http.HomeNetWork
import cn.sichuan.cd.zsh.ui.http.HomeRepository


object InjectorUtil {

    fun getHomeRepository() = HomeRepository.getInstance(HomeNetWork.getInstance())

}