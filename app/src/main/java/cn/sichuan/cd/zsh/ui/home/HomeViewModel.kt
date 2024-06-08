package cn.sichuan.cd.zsh.ui.home

import androidx.lifecycle.LiveData
import cn.sichuan.cd.zsh.model.app.base.BaseViewModel
import cn.sichuan.cd.zsh.model.event.SingleLiveEvent
import cn.sichuan.cd.zsh.model.extend.asResponse
import cn.sichuan.cd.zsh.model.extend.bindLoading
import cn.sichuan.cd.zsh.ui.utils.InjectorUtil
import cn.sichuan.cd.zsh.ui.network.entity.BannerBean
import cn.sichuan.cd.zsh.ui.network.HomeListBean

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.onCompletion

class HomeViewModel : BaseViewModel() {

    private val homeRepository by lazy { InjectorUtil.getHomeRepository() }

    private val _banners = MutableSharedFlow<List<BannerBean>>()
    val mBanners: SharedFlow<List<BannerBean>> = _banners

    private val _projectData = MutableSharedFlow<HomeListBean>()
    val projectData: SharedFlow<HomeListBean> = _projectData

    private val _refreshState = SingleLiveEvent<Boolean>()
    val refreshState: LiveData<Boolean> = _refreshState

    /**
     * Banner
     */
    fun getBanner(refresh: Boolean = false) {
        launch {
            homeRepository.getBannerData(refresh)
                .asResponse()
                .collect(_banners)
        }
    }

    /**
     * @param page 页码
     * @param refresh 是否刷新
     */
    fun getHomeList(page: Int, refresh: Boolean = false) {
        launch {
            homeRepository.getHomeList(page, refresh)
                .asResponse()
                .onCompletion { if (refresh) _refreshState.call() }
                .bindLoading(this@HomeViewModel)
                .collect(_projectData)
        }
    }
}
