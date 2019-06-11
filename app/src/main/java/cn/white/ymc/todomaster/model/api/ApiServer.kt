package cn.white.ymc.todomaster.model.api

import cn.white.ymc.todomaster.data.AllListResponse
import cn.white.ymc.todomaster.data.BaseResp
import cn.white.ymc.todomaster.data.ListResponse
import cn.white.ymc.todomaster.data.UserBean
import io.reactivex.Observable
import retrofit2.http.*

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
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<BaseResp<UserBean>>

    /**
     * 注册
     * @param username       用户名
     * @param password       密码
     * @param verifyPassword 验证密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("username") username: String, @Field("password") password: String,
                 @Field("repassword") verifyPassword: String): Observable<BaseResp<UserBean>>

    /**
     * 返回列表数据
     * @param type 类型
     * @return
     */
    @GET("lg/todo/list/{type}/json")
    fun getList(@Path("type") type: Int): Observable<BaseResp<AllListResponse>>


    /**
     * 删除
     * @param id
     * @return
     */
    @POST("lg/todo/delete/{id}/json")
    fun delete(@Path("id") id: Int): Observable<BaseResp<Unit>>

    /**
     * 分页获取已完成Todo列表
     * @param type 类型
     * @param page 页码
     * @return
     */
    @GET("lg/todo/listdone/{type}/json/{page}")
    fun getListDone(@Path("type") type: Int, @Path("page") page: Int):
            Observable<BaseResp<ListResponse>>

    /**
     * 分页 获取未完成Todo列表
     * @param type 类型
     * @param page 页码
     * @return
     */
    @GET("lg/todo/listnotdo/{type}/json/{page}")
    fun getListNotDo(@Path("type") type: Int, @Path("page") page: Int):
            Observable<BaseResp<ListResponse>>

}