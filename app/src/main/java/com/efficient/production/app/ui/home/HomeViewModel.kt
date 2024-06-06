package com.efficient.production.app.ui.home

import androidx.lifecycle.LiveData
import com.efficient.production.app.model.app.base.BaseViewModel
import com.efficient.production.app.model.event.SingleLiveEvent
import com.efficient.production.app.model.extend.asResponse
import com.efficient.production.app.model.extend.bindLoading
import com.efficient.production.app.ui.utils.InjectorUtil
import com.efficient.production.app.ui.network.entity.BannerBean
import com.efficient.production.app.ui.network.HomeListBean

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
