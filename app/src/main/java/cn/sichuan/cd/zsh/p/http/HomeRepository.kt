package cn.sichuan.cd.zsh.p.http

//import com.aleyn.cache.CacheMode
//import com.aleyn.mvvm.base.BaseModel
//import com.pcl.mvvm.app.base.BaseResult
//import com.pcl.mvvm.data.http.HomeNetWork
import cn.sichuan.cd.zsh.model.app.base.BaseResult
import cn.sichuan.cd.zsh.model.base.BaseModel
import com.pcl.mvvm.network.entity.BannerBean
import com.pcl.mvvm.network.entity.HomeListBean
import kotlinx.coroutines.flow.Flow

/**
 *   @author : Aleyn
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