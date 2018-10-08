package cn.white.ymc.todomaster.model.cookie

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 *  cookie 管理工具
 *
 * @packageName: cn.white.ymc.todomaster.model.cookie
 * @fileName: CookiesManager
 * @date: 2018/10/8  13:50
 * @author: ymc
 * @QQ:745612618
 */

class CookiesManager : CookieJar {

    private val cookieStore = PersistentCookieStore()

    override fun loadForRequest(url: HttpUrl?): MutableList<Cookie> {


    }

    override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
    }

}