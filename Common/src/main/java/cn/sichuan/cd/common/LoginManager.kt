package cn.sichuan.cd.common

import android.util.Log

object LoginManager {

    /**
     *
     * 数据来源
     * 多模块模式
     * https://www.bilibili.com/video/BV1Ar4y1A7kh/?spm_id_from=333.337.search-card.all.click&vd_source=83fdbd83d34aa2af54f70627e76de772
     *
     */


    fun login(username: String, password: String): Boolean {

        Log.d("--------------LoginManager----------","--------------${username}-------${password}---------");
        // Implement login logic here
        return username == "admin" && password == "password"
    }
}