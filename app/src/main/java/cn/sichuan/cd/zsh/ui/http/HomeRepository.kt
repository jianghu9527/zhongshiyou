package cn.sichuan.cd.zsh.ui.http

 
import cn.sichuan.cd.zsh.model.app.base.BaseModel
import cn.sichuan.cd.zsh.model.app.base.BaseResult
import cn.sichuan.cd.zsh.ui.network.entity.BannerBean
import cn.sichuan.cd.zsh.ui.network.HomeListBean
import kotlinx.coroutines.flow.Flow

/**
 *   @author : ck
 *   time   : 2019/10/29
 */
class HomeRepository private constructor(
    private val netWork: HomeNetWork
) : BaseModel() {

    fun getBannerData(refresh: Boolean): Flow<BaseResult<List<BannerBean>>> {
        val cacheModel ="READ_CACHE_NETWORK_PUT";
        return netWork.getBannerData(cacheModel)
    }

    fun getHomeList(page: Int, refresh: Boolean = false): Flow<BaseResult<HomeListBean>> {
        val cacheModel = "READ_CACHE_NETWORK_PUT"
        return netWork.getHomeList(page, cacheModel)
    }

    suspend fun getNaviJson() = netWork.getNaviJson()

    suspend fun getProjectList(page: Int, cid: Int) = netWork.getProjectList(page, cid)

    suspend fun getPopularWeb() = netWork.getPopularWeb()

    companion object {

        @Volatile
        private var INSTANCE: HomeRepository? = null

        fun getInstance(netWork: HomeNetWork) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: HomeRepository(netWork).also { INSTANCE = it }
            }
    }
}