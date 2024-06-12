package cn.sichuan.cd.zzsy.model.app.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.sichuan.cd.zzsy.model.app.MVVMLin
import cn.sichuan.cd.zzsy.model.event.Message
import cn.sichuan.cd.zzsy.model.event.SingleLiveEvent

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 *   @author : ck
 *   time   : 2019/11/01
 */
open class BaseViewModel : ViewModel(), IViewModel, DefaultLifecycleObserver {

    val defUI: UIChange by lazy { UIChange() }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    fun launch(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(MVVMLin.netException) { block() }

    /**
     * UI事件
     */
    inner class UIChange {
//        val showDialog by lazy { SingleLiveEvent<String>() }
val showDialog by lazy { MutableSharedFlow<String>() }
        val dismissDialog by lazy { MutableSharedFlow<Unit>() }
        val msgEvent by lazy { MutableSharedFlow<Message>() }
    }

    override fun showLoading(text: String) {
//        defUI.showDialog.postValue(text)
        launch { defUI.showDialog.emit(text) }
    }

    override fun dismissLoading() {
//        defUI.dismissDialog.call()
        launch { defUI.dismissDialog.emit(Unit) }
    }
}