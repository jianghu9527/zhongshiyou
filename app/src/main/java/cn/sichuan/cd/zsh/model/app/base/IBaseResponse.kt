package cn.sichuan.cd.zsh.model.app.base

/**
 *   @author : ck
 *   time   : 2020/01/13
 */
interface IBaseResponse<T> {
    fun code(): Int
    fun msg(): String
    fun data(): T
    fun isSuccess(): Boolean
}