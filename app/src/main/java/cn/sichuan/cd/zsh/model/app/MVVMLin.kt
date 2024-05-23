package cn.sichuan.cd.zsh.model.app

import cn.sichuan.cd.zsh.model.network.ExceptionHandle
import com.blankj.utilcode.util.ToastUtils
import kotlinx.coroutines.CoroutineExceptionHandler


object MVVMLin {

    private val defNetException = CoroutineExceptionHandler { _, throwable ->
        val exception = ExceptionHandle.handleException(throwable)
        ToastUtils.showShort(exception.errMsg)
    }

    val netException: CoroutineExceptionHandler
        get() = customNetException ?: defNetException


    private var customNetException: CoroutineExceptionHandler? = null


    fun setNetException(netException: CoroutineExceptionHandler) = apply {
        customNetException = netException
    }

}