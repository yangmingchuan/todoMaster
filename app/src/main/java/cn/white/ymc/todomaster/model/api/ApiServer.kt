package cn.white.ymc.todomaster.model.api

import cn.white.ymc.todomaster.data.*
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
    fun listDone(@Path("type") type: Int, @Path("page") page: Int):
            Observable<BaseResp<ListResponse>>

    /**
     * 分页 获取未完成Todo列表
     * @param type 类型
     * @param page 页码
     * @return
     */
    @GET("lg/todo/listnotdo/{type}/json/{page}")
    fun listNotDo(@Path("type") type: Int, @Path("page") page: Int):
            Observable<BaseResp<ListResponse>>

    /**
     * 新增一条Todo
     * @param title
     * @param content
     * @param date
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("lg/todo/add/json")
    fun add(@Field("title") title: String, @Field("content") content: String,
            @Field("date") date: String, @Field("type") type: Int):
            Observable<BaseResp<TodoDetail>>

    /**
     * 仅更新完成状态Todo
     * @param id
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("lg/todo/done/{id}/json")
    fun done(@Path("id") id: Int, @Field("status") status: Int):
            Observable<BaseResp<TodoDetail>>

    /**
     * 更新一条Todo内容
     * @param id
     * @param title
     * @param content
     * @param date
     * @param status
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("lg/todo/update/{id}/json")
    fun update(@Path("id") id: Int, @Field("title") title: String,
               @Field("content") content: String, @Field("date") date: String,
               @Field("status") status: Int, @Field("type") type: Int):
            Observable<BaseResp<TodoDetail>>

}