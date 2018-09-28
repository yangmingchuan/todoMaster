package cn.white.ymc.todomaster.model.api

import okhttp3.OkHttpClient

/**
 *  api 请求中心
 *
 * @packageName: cn.white.ymc.todomaster.model.api
 * @fileName: ApiStore
 * @date: 2018/9/28  15:17
 * @author: ymc
 * @QQ:745612618
 */

class ApiStore constructor() {

    private val apiServer: ApiServer? = null

    /**
     * 初始化 retrofit
     */
    init {
        OkHttpClient().newBuilder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
    }


}