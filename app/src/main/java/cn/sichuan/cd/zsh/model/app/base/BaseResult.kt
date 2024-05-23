package cn.sichuan.cd.zsh.model.app.base

import cn.sichuan.cd.zsh.model.base.IBaseResponse


data class BaseResult<T>(
    val errorMsg: String,
    val errorCode: Int,
    val data: T
) : IBaseResponse<T> {

    override fun code() = errorCode

    override fun msg() = errorMsg

    override fun data() = data

    override fun isSuccess() = errorCode == 0

}