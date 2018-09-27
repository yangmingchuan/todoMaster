package cn.white.ymc.todomaster.utils.cache

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import cn.white.ymc.todomaster.base.MApplication
import cn.white.ymc.todomaster.utils.ConstantUtil
import java.lang.reflect.Method

/**
 * sp 工具类
 *
 * @packageName: cn.white.ymc.todomaster.utils.cache
 * @fileName: SPUtil
 * @date: 2018/9/20  14:10
 * @author: ymc
 * @QQ:745612618
 */

/**
 *  通过 apply 方法的一个兼容类
 */
class SPUtil constructor(){

    var sApplyMethod : Method? = findApplyMethod()
    val sp =  MApplication.instance.getSharedPreferences(ConstantUtil.SP_FILE_NAME, Context.MODE_PRIVATE)
    val editor = sp.edit()

    companion object {
        val instance: SPUtil by lazy{
            SPUtil()
        }
    }

    private fun findApplyMethod(): Method?{
        try {
            val clz = SharedPreferences.Editor::class.java
            return clz.getMethod("apply")
        }catch (e:NoSuchMethodException){
        }
        return null
    }

    fun applySp(editor : SharedPreferences.Editor){
        if(sApplyMethod!=null){
            sApplyMethod!!.invoke(editor)
            return
        }
        editor.commit()
    }

    /**
     * sp 写入
     * 使用when is 判断类型如果是就写入
     */
    @SuppressLint("CommitPrefEdits")
    fun spPutValue(key:String , value: Any){
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            else -> editor.putString(key, value.toString())
        }
        applySp(editor)
    }

    /**
     * sp 读取
     */
    fun spGetValue(key : String , defaultValue : Any):Any?{
        when (defaultValue){
            is String -> return sp.getString(key, defaultValue)
            is Int -> return sp.getInt(key, defaultValue)
            is Boolean -> return sp.getBoolean(key, defaultValue)
            is Float -> return sp.getFloat(key, defaultValue)
            is Long -> return sp.getLong(key, defaultValue)
        }
        return null
    }

    /**
     *  移除掉其中的一个
     */
    fun spRemove (key : String){
        editor.remove(key)
        applySp(editor)
    }

    /**
     * 移除全部
     */
    fun spRemoveAll(){
        editor.clear()
        applySp(editor)
    }

}



