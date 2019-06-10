package cn.white.ymc.todomaster.data

/**
 * bean base
 *
 * @packageName: cn.white.ymc.todomaster.data
 * @fileName: BaseResp
 * @date: 2018/9/28  16:12
 * @author: ymc
 * @QQ:745612618
 */

data class BaseResp<T>(
        val errorMsg: String,
        val errorCode: Int,
        val data: T?
)
