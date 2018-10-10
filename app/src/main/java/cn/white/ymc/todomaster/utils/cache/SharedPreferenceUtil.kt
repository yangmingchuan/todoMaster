package cn.white.ymc.todomaster.utils.cache

import android.content.Context
import android.content.SharedPreferences
import cn.white.ymc.todomaster.utils.ConstantUtil
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * SharedPreferences 工具类
 *  1. ReadWriteProperty 委托模式
 *
 * @packageName: cn.white.ymc.todomaster.utils.cache
 * @fileName: SharedPreferenceUtil
 * @date: 2018/10/9  16:01
 * @author: ymc
 * @QQ:745612618
 */

class SharedPreferenceUtil<T> ( private var key : String ,private var value : T): ReadWriteProperty<Any?, T> {

    /**
     * 伴生对象
     */
    companion object {
        var mSP : SharedPreferences? = null

        /**
         * 初始化
         */
        fun initSP(context : Context){
            mSP = context.getSharedPreferences(context.packageName + ConstantUtil.SP_FILE_NAME,
                    Context.MODE_PRIVATE
            )
        }

        fun clearSP(){
            mSP!!.edit().clear().apply()
        }

    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {

    }

}
