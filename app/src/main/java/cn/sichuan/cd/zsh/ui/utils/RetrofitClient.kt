package cn.sichuan.cd.zsh.ui.utils

import android.util.Log
import cn.sichuan.cd.zsh.model.adapter.FlowAdapterFactory
import cn.sichuan.cd.zsh.ui.common.Constant.BASE_URL
import cn.sichuan.cd.zsh.utils.LLogUtils
import cn.sichuan.cd.zsh.zsh.LogMangeUtil
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {

    init {
        try {
            retrofit = Retrofit.Builder()
                .client(getOkHttpClient())
                .addCallAdapterFactory(FlowAdapterFactory.create(true))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        } catch (e: Exception) {
            Log.e("--------------RetrofitClient", "Initialization failed", e)
        }
    }

    companion object {
        fun getInstance() = SingletonHolder.INSTANCE
        private lateinit var retrofit: Retrofit
    }

    private object SingletonHolder {
        val INSTANCE = RetrofitClient()
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20L, TimeUnit.SECONDS)
            .writeTimeout(20L, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
            .addInterceptor(  HttpLoggingInterceptor().apply {
                LLogUtils.log("----------------level---------${HttpLoggingInterceptor.Level.BASIC}")
                level = HttpLoggingInterceptor.Level.BODY
            })
//            .addInterceptor(interceptor)
            .build()
    }

    fun <T> create(service: Class<T>?): T {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit.create(service)
    }

    private val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            try {
                val text = URLDecoder.decode(message, "utf-8")
                LogMangeUtil.e("--------------------OKHttp-----", text)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
                LogMangeUtil.e("---------------OKHttp-----", message)
            }
        }
    })
}




