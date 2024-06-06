package com.efficient.production.app.ui.http

import com.efficient.production.app.ui.network.api.HomeService
import com.efficient.production.app.ui.utils.RetrofitClient


/**
 *   @author : ck
 *   time   : 2019/11/01
 */
class HomeNetWork {

    private val mService by lazy { RetrofitClient.getInstance().create(HomeService::class.java) }

    fun getBannerData(cacheModel: String) = mService.getBanner(cacheModel)

    fun getHomeList(page: Int, cacheModel: String) = mService.getHomeList(page, cacheModel)

    suspend fun getNaviJson() = mService.naviJson()

    suspend fun getProjectList(page: Int, cid: Int) = mService.getProjectList(page, cid)

    suspend fun getPopularWeb() = mService.getPopularWeb()


    companion object {
        @Volatile
        private var netWork: HomeNetWork? = null

        fun getInstance() = netWork ?: synchronized(this) {
            netWork ?: HomeNetWork().also { netWork = it }
        }
    }


}