package cn.sichuan.cd.zzsy.ui.me


import cn.sichuan.cd.zzsy.model.app.base.BaseViewModel
import cn.sichuan.cd.zzsy.model.extend.getOrThrow
import cn.sichuan.cd.zzsy.ui.utils.InjectorUtil
import cn.sichuan.cd.zzsy.ui.network.UsedWeb

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 *   @auther : ck
 *   time   : 2019/11/14
 */
class MeViewModel : BaseViewModel() {

    private val homeRepository by lazy { InjectorUtil.getHomeRepository() }

    private val _popularWeb = MutableSharedFlow<MutableList<UsedWeb>>()
    val popularWeb: SharedFlow<MutableList<UsedWeb>> = _popularWeb


    fun getPopularWeb() {
        launch {
            homeRepository.getPopularWeb().getOrThrow().let {
                _popularWeb.emit(it)
            }
        }
    }
}