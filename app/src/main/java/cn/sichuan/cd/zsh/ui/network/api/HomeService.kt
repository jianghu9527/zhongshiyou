package cn.sichuan.cd.zzsy.ui.network.api

//import com.ck.cache.CacheStrategy
import cn.sichuan.cd.zzsy.model.app.base.BaseResult
import cn.sichuan.cd.zzsy.ui.network.entity.BannerBean
import cn.sichuan.cd.zzsy.ui.network.HomeListBean
import cn.sichuan.cd.zzsy.ui.network.NavTypeBean
import cn.sichuan.cd.zzsy.ui.network.UsedWeb
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface HomeService {

    /**
     * 玩安卓轮播图
     */
    @GET("banner/json")
    fun getBanner(
        @Header("Cache-Control") cacheModel: String
    ): Flow<BaseResult<List<BannerBean>>>


    /**
     * 导航数据
     */
    @GET("project/tree/json")
    suspend fun naviJson(): BaseResult<List<NavTypeBean>>


    /**
     * 项目列表
     * @param page 页码，从0开始
     */
    @GET("article/listproject/{page}/json")
    fun getHomeList(
        @Path("page") page: Int,
        @Header("Cache-Control") cacheModel: String
    ): Flow<BaseResult<HomeListBean>>


    /**
     * 项目列表
     * @param page 页码，从0开始
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseResult<HomeListBean>


    /**
     * 常用网站
     */
    @GET("friend/json")
    suspend fun getPopularWeb(): BaseResult<MutableList<UsedWeb>>
}