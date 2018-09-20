package cn.white.ymc.todomaster.utils.cache

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Process
import cn.white.ymc.todomaster.utils.eLogger
import cn.white.ymc.todomaster.utils.getDateEN
import java.io.*

/**
 *  异常本地持久化工具类
 *
 * @packageName: cn.white.ymc.todomaster.utils.cache
 * @fileName: CrashHandler
 * @date: 2018/9/19  17:35
 * @author: ymc
 * @QQ:745612618
 */

class CrashHandler constructor(context: Context) : Thread.UncaughtExceptionHandler {
    var TAG: String = "CrashHandler"
    var DEBUG: Boolean = true
    val PATH: String = Environment.getExternalStorageDirectory().getPath() + "/todo/errLog/"
    val FILE_NAME: String = "crash"
    private val FILE_NAME_SUFFIX = ".trace"
    private var mExceptionHandler: Thread.UncaughtExceptionHandler? =
            Thread.getDefaultUncaughtExceptionHandler()
    private var mContext: Context? = context.applicationContext

    val instance: CrashHandler by lazy {
        CrashHandler(mContext!!)
    }

    fun initCarsh() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     */
    override fun uncaughtException(t: Thread?, e: Throwable?) {
        try {
            dumpExceptionToSDCard(e)
            uploadExceptionToServer()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //打印出当前调用栈信息
        e!!.printStackTrace()
        //如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
        if (mExceptionHandler != null) {
            mExceptionHandler!!.uncaughtException(t, e)
        } else {
            Process.killProcess(Process.myPid())
        }
    }

    /**
     *  本地持久化
     */
    private fun dumpExceptionToSDCard(e: Throwable?) {
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if(DEBUG){
                eLogger("sdcard unmounted,skip dump exception")
                return
            }
        }
        var dir = File(PATH)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        var file = File(PATH + FILE_NAME + getDateEN() + FILE_NAME_SUFFIX)
        var mPrintWriter = PrintWriter(BufferedWriter(FileWriter(file)))
        //导出发生异常的时间
        mPrintWriter.println(getDateEN())
        dumpPhoneInfo(mPrintWriter)
        mPrintWriter.println()
        //导出异常的调用栈信息
        e!!.printStackTrace(mPrintWriter)
        mPrintWriter.close()
    }

    /**
     *  导出 手机 相关信息
     */
    @Throws(PackageManager.NameNotFoundException::class)
    private fun dumpPhoneInfo(pw: PrintWriter) {
        /**
         *  应用的版本名称和版本号
         */
        val pm = mContext!!.packageManager
        val pi = pm.getPackageInfo(mContext!!.packageName, PackageManager.GET_ACTIVITIES)
        pw.print("App Version: ")
        pw.print(pi.versionName)
        pw.print('_')
        pw.println(pi.versionCode)
        /**
         * android版本号
         */
        pw.print("OS Version: ")
        pw.print(Build.VERSION.RELEASE)
        pw.print("_")
        pw.println(Build.VERSION.SDK_INT)
        /**
         * 手机制造商
         */
        pw.print("Vendor: ")
        pw.println(Build.MANUFACTURER)
        /**
         * 手机型号
         */
        pw.print("Model: ")
        pw.println(Build.MODEL)
        /**
         *  cpu架构
         */
        pw.print("CPU ABI: ")
        pw.println(Build.CPU_ABI)
    }

    /**
     * 提交到 服务器
     */
    private fun uploadExceptionToServer() {

    }

}

