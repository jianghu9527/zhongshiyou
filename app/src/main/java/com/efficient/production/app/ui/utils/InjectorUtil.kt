package com.efficient.production.app.ui.utils

import com.efficient.production.app.ui.http.HomeNetWork
import com.efficient.production.app.ui.http.HomeRepository


object InjectorUtil {

    fun getHomeRepository() = HomeRepository.getInstance(HomeNetWork.getInstance())

}