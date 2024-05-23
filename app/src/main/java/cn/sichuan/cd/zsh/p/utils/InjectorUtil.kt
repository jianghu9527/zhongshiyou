package cn.sichuan.cd.zsh.p.utils

import cn.sichuan.cd.zsh.p.http.HomeNetWork
import cn.sichuan.cd.zsh.p.http.HomeRepository


object InjectorUtil {

    fun getHomeRepository() = HomeRepository.getInstance(HomeNetWork.getInstance())

}