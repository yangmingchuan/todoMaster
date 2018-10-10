package cn.white.ymc.todomaster.model.cookie

import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import cn.white.ymc.todomaster.base.MApplication
import okhttp3.Cookie
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import cn.white.ymc.todomaster.utils.*

/**
 * cookie 本地持久化
 *
 * @packageName: cn.white.ymc.todomaster.model.cookie
 * @fileName: PersistentCookieStore
 * @date: 2018/10/8  14:19
 * @author: ymc
 * @QQ:745612618
 */

class PersistentCookieStore {

    private val COOKIE_PREFS = "Cookies_Prefs"
    private var cookies: MutableMap<String, ConcurrentMap<String, Cookie>>? = null
    private var cookiePrefs: SharedPreferences? = null

    /**
     * 初始化
     */
    init {
        cookiePrefs = MApplication.instance.getSharedPreferences(COOKIE_PREFS,0)
        cookies = hashMapOf()

        // 获取持久化 cookies
        val cookieMap = cookiePrefs!!.all
        for (entry in cookieMap.entries) {
            val cookieNames = TextUtils.split(entry.value as String, ",")
            for (name in cookieNames) {
                val encodedCookie = cookiePrefs!!.getString(name, null)
                if (encodedCookie != null) {
                    val decodedCookie = decodeCookie(encodedCookie)
                    if (decodedCookie != null) {
                        if (!cookies!!.containsKey(entry.key)) {
                            cookies!!.put(entry.key, ConcurrentHashMap())
                        }
                        cookies[entry.key]!!.put(name, decodedCookie)
                    }
                }
            }
        }
    }

    /**
     *  获取 cookie
     */
    private fun decodeCookie(encodedCookie: String?): Cookie? {
        val bytes = hexStringToByteArray(encodedCookie)
        val byteArrayInputStream = ByteArrayInputStream(bytes)
        var cookie: Cookie? = null
        try {
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            cookie = (objectInputStream.readObject() as OkHttpCookies).getCookies()
        } catch (ex: IOException) {
            e("IOException in decodeCookie "+ex)
        } catch (ex: ClassNotFoundException) {
            e( "ClassNotFoundException in decodeCookie " + ex )
        }
        return cookie

    }


}