package cn.sichuan.cd.zsh.ui.me


import cn.sichuan.cd.zsh.model.base.BaseViewModel
import cn.sichuan.cd.zsh.model.extend.getOrThrow
import cn.sichuan.cd.zsh.ui.utils.InjectorUtil
import com.pcl.mvvm.network.entity.UsedWeb

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 *   @auther : Aleyn
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