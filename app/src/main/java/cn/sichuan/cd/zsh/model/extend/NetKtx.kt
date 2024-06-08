package cn.sichuan.cd.zsh.model.extend

import cn.sichuan.cd.zsh.model.app.base.IBaseResponse
import cn.sichuan.cd.zsh.model.app.base.IViewModel
import cn.sichuan.cd.zsh.model.network.ERROR
import cn.sichuan.cd.zsh.model.network.ExceptionHandle
import cn.sichuan.cd.zsh.model.network.ResponseThrowable


import kotlinx.coroutines.flow.*


/**
 * Flow 转换结果
 */
fun <T> Flow<IBaseResponse<T>>.asResponse(): Flow<T> = transform { value ->
    return@transform if (value.isSuccess() && value.data() != null) {
        emit(value.data())
    } else if (value.isSuccess() && value.data() == null) {
        throw ResponseThrowable(ERROR.DATA_NULL)
    } else throw ResponseThrowable(value)
}

/**
 * 转换结果(只在成功后做处理，不关心 data 值)
 */
fun <T> Flow<IBaseResponse<T>>.asSuccess(): Flow<T> = transform { value ->
    return@transform if (value.isSuccess()) {
        emit(value.data())
    } else throw ResponseThrowable(value)
}

/**
 * 绑定Loading
 */
fun <T> Flow<T>.bindLoading(model: IViewModel, text: String = "") = onStart {
    model.showLoading(text)
}.onCompletion {
    model.dismissLoading()
}


/**
 * 网络错误
 */
fun <T> Flow<T>.netCache(action: (ResponseThrowable) -> Unit) = catch {
    val exception = ExceptionHandle.handleException(it)
    action(exception)
}

/**
 * 只取成功结果其他抛异常
 */
fun <T> IBaseResponse<T>.getOrThrow(): T {
    return if (isSuccess() && data() != null) {
        data()
    } else if (isSuccess() && data() == null) {
        throw ResponseThrowable(ERROR.DATA_NULL)
    } else throw ResponseThrowable(this)
}

/**
 * 检测成功结果其他抛异常（不关心 data ）
 */
fun <T> IBaseResponse<T>.check() {
    if (!isSuccess()) throw ResponseThrowable(this)
}