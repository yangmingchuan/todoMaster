package cn.white.ymc.todomaster.utils.cache

/**
 *  异常本地持久化工具类
 *
 * @packageName: cn.white.ymc.todomaster.utils.cache
 * @fileName: CrashHandler
 * @date: 2018/9/19  17:35
 * @author: ymc
 * @QQ:745612618
 */

object CrashHandler : Thread.UncaughtExceptionHandler{
    var TAG : String = "CrashHandler"
    var DEBUG : Boolean = true

    override fun uncaughtException(t: Thread?, e: Throwable?) {

    }


}
