package cn.white.ymc.todomaster.model.api

import cn.white.ymc.todomaster.data.BaseResp
import cn.white.ymc.todomaster.data.UserBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * BaseResp  请求接口
 *
 * @packageName: cn.white.ymc.todomaster.model.api
 * @fileName: BaseResp
 * @date: 2018/9/28  15:51
 * @author: ymc
 * @QQ:745612618
 */

interface ApiServer {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<BaseResp<UserBean>>

}