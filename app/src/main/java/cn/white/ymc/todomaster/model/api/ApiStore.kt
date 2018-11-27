package cn.white.ymc.todomaster.model.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit

/**
 *  api 请求中心
 *
 * @packageName: cn.white.ymc.todomaster.model.api
 * @fileName: ApiStore
 * @date: 2018/9/28  15:17
 * @author: ymc
 * @QQ:745612618
 */

object ApiStore {

    private const val TAG = "ApiStore"
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 15L
    private const val USER_LOGIN_URL = "user/login"
    private const val USER_REGISTER_URL = "user/register"
    private const val SET_COOKIE_KEY = "set-cookie"
    private const val COOKIE_NAME = "Cookie"


    /**
     * 创建 OkHttp Client
     */
    private fun create(): Retrofit{
        val okHttpClientBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
            // 添加 cookie 本地持久化
            addInterceptor {
                val request = it.request()
                val response = it.proceed(request)
                val requestUrl = request.url().toString()
                val domain = request.url().host()
                // 仅在登录注册 进行 cookie保存
                if((requestUrl.contains(USER_LOGIN_URL)||requestUrl.contains(USER_REGISTER_URL)) &&
                        !response.headers(SET_COOKIE_KEY).isEmpty()){
                    val cookies = response.headers(SET_COOKIE_KEY)
                    val cookie = encodeCookie(cookies)
                }

                response
            }
        }

        return
    }

    /**
     *  list 转为 string
     */
    private fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val linkList = LinkedList<String>()
        /**
         *  it.split(";".toRegex()) 为了防止 list item 中存在cookies1 ； cookies2情况
         *  dropLastWhile 去掉满足{}中条件的指定元素
         *  toTypedArray list 转为 array
         *
         *  filterNot  过滤所有不符合给定函数条件的元素
         */
        cookies.map {
            it.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }.forEach {
            it.filterNot { linkList.contains(it) }.forEach { linkList.add(it) }
        }

        linkList.forEach {
            sb.append(it).append(";")
        }

        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }


}