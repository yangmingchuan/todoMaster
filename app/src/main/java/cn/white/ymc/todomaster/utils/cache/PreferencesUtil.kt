package cn.white.ymc.todomaster.utils.cache

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import cn.white.ymc.todomaster.utils.ConstantUtil
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Shared Preferences  保存读取工具类
 *
 * @packageName: cn.white.ymc.todomaster.utils.cache
 * @fileName: PreferencesUtil
 * @date: 2018/11/28  10:11
 * @author: ymc
 * @QQ:745612618
 */

class PreferencesUtil<T> (private val key: String, private val value: T) :ReadWriteProperty<Any?,T>{

    companion object {
        lateinit var preferences : SharedPreferences

        /**
         * 初始化 sp
         */
        fun attch(context : Context){
            preferences = context.getSharedPreferences(context.packageName + ConstantUtil.SP_FILE_NAME,
                    Context.MODE_PRIVATE)
        }

        fun clear(){
            preferences.edit().clear().apply()
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return  findPreference(key, value)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putPreference(key, value)

    /**
     * 获取 sp value
     * 多次使用到 preferences 我们可以使用with 抽出来
     */
    @Suppress("UNCHECKED_CAST")
    private fun <T> findPreference(name: String, default: T): T {
        val res: Any = when (default){
            is Long -> preferences.getLong(name,default)
            is String -> preferences.getString(name, default)
            is Int -> preferences.getInt(name, default)
            is Boolean -> preferences.getBoolean(name, default)
            is Float -> preferences.getFloat(name, default)
            else -> throw IllegalArgumentException("get sp type error")
        }
        return res as T
    }

    /**
     *  添加 sp value
     */
    @SuppressLint("CommitPrefEdits")
    private fun <T> putPreference(key: String, value: T)  = with(preferences.edit()){
        when (value){
            is Long -> putLong(key,value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> throw IllegalArgumentException("put sp type error")
        }.apply()
    }
}