package cn.white.ymc.todomaster.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.orhanobut.logger.Logger
import java.text.SimpleDateFormat
import java.util.*

/**
 * app 工具类
 *
 * @packageName: cn.white.ymc.todomaster.utils
 * @fileName: ToastUtil
 * @date: 2018/9/19  9:37
 * @author: ymc
 * @QQ:745612618
 */

var isShow: Boolean = true
var isDebug: Boolean = true
var appTag: String = "todo"

/**
 * 显示 短 toast
 *
 * apply 方法内 可以调用该对象的任意方法，并返回该对象
 * 如果左边不是空就返回左边，如果左右是空，则会走右边的 run
 */
fun Context.toast( msg: CharSequence) {
    if(isShow){
        ConstantUtil.toast?.apply {
            setText(msg)
            show()
        }?:run {
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).apply {
                ConstantUtil.toast = this
            }.show()
        }
    }
}

/**
 * 显示 长 toast
 */
fun Context.ToastL(msg : CharSequence){
    if(isShow){
        ConstantUtil.toast?.apply {
            setText(msg)
            show()
        }?:apply {
            Toast.makeText(this,msg,Toast.LENGTH_LONG).apply {
                ConstantUtil.toast = this
            }.show()
        }
    }
}

/**
 * log i
 */
fun i(tag: String, msg: String) {
    if (isDebug) {
        Log.i(tag, msg)
    }
}

/**
 * log todo e
 */
fun loge(msg: String) {
    if (isDebug) {
        Log.e(appTag, msg)
    }
}

/**
 * logger e
 */
fun eLogger(msg: String) {
    if (isDebug) {
        Logger.e(msg)
    }
}

/**
 * logger w
 */
fun wLogger(msg: String) {
    if (isDebug) {
        Logger.w(msg)
    }
}


/**
 * 获取时间
 */
@SuppressLint("SimpleDateFormat")
fun getDateTime(): String {
    var format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(Date(System.currentTimeMillis()))
}

/**
 * 获取时间  时分秒
 */
@SuppressLint("SimpleDateFormat")
fun getDateEN(): String {
    var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return format.format(Date(System.currentTimeMillis()))
}

/**
 *  随机获取 颜色
 * @return 16777215 is FFFFFF, 0 is 000000
 */
fun getRandomColor(): String = "#${Integer.toHexString((Math.random() * 16777215).toInt())}"

