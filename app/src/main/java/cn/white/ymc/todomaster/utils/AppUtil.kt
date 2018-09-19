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
 * 显示toast
 */

fun show(context: Context, message: CharSequence, duration: Int) {
    if (isShow) {
        Toast.makeText(context, message, duration).show()
    }
}

/**
 * 显示 短 toast
 */
fun showShort(context: Context, message: CharSequence) {
    show(context, message, Toast.LENGTH_SHORT)
}

/**
 * 显示 长 toast
 */
fun showLong(context: Context, message: CharSequence) {
    show(context, message, Toast.LENGTH_LONG)
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
 * log e
 */
fun e(tag: String, msg: String) {
    if (isDebug) {
        Log.e(tag, msg)
    }
}

/**
 * log todo e
 */
fun e(msg: String) {
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

