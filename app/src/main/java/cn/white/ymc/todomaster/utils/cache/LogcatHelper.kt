package cn.white.ymc.todomaster.utils.cache

import android.content.Context
import android.os.Environment
import cn.white.ymc.todomaster.utils.eLogger
import cn.white.ymc.todomaster.utils.getDateEN
import cn.white.ymc.todomaster.utils.getDateTime
import java.io.*

/**
 * 本地log 持久化工具类
 *
 * @packageName: cn.white.ymc.todomaster.utils.log
 * @fileName: LogcatHelper
 * @date: 2018/9/19  13:24
 * @author: ymc
 * @QQ:745612618
 */

class LogcatHelper constructor(context: Context) {

    private var pathLogcat: String? = null
    private var mLogDumper: LogDumper? = null
    private var mPid: Int = 0
    var mcontext: Context? = context.applicationContext

    val instance: LogcatHelper by lazy {
        LogcatHelper(mcontext!!)
    }

    init {
        initLogcat(mcontext!!)
        mPid = android.os.Process.myPid()
    }

    /**
     * 打开读取子线程
     */
    fun startLogThread() {
        if (mLogDumper != null) {
            mLogDumper = LogDumper(mPid.toString(), pathLogcat!!)
        }
        mLogDumper!!.start()
    }

    /**
     * 停止 子线程
     */
    fun stopLogThread() {
        if (mLogDumper != null) {
            mLogDumper!!.stopLogs()
            mLogDumper = null
        }
    }

    /**
     *
     * 初始化目录
     *
     */
    fun initLogcat(context: Context) {
        // 优先保存到SD卡中
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            pathLogcat = Environment.getExternalStorageDirectory()
                    .absolutePath + File.separator + "AWanAndroid-log"
        } else {// 如果SD卡不存在，就保存到本应用的目录下
            pathLogcat = (context.filesDir.absolutePath
                    + File.separator + "AWanAndroid-log")
        }
        val file = File(pathLogcat)
        if (!file.exists()) {
            file.mkdirs()
        }
    }

    /**
     * 读取线程
     * 只有主构造函数 constructor 可以省略
     */
    private class LogDumper(pid: String, dir: String) : Thread() {
        /**
         * 日志等级：*:v , *:d , *:w , *:e , *:f , *:s
         * 显示当前程序的 E 等级的日志.
         * */
        var cmds = "logcat -s TAG"
        var mprocess: Process? = null
        var mReader: BufferedReader? = null
        var mRunning = true
        var mPID: String? = null
        var out: FileOutputStream? = null

        /**
         * init 初始化块
         */
        init {
            mPID = pid
            out = FileOutputStream(File(dir, "todo-" + getDateTime() + ".log"))
        }

        /**
         * 停止
         */
        fun stopLogs() {
            mRunning = false
        }

        override fun run() {
            try {
                mprocess = Runtime.getRuntime().exec(cmds)
                mReader = BufferedReader(InputStreamReader(mprocess!!.inputStream), 1024)
                var line: String? = null
                while (mRunning && mReader!!.readLine() != null) {
                    line = mReader!!.readLine()
                    if (!mRunning) {
                        break
                    }
                    if (line.isEmpty()) {
                        continue
                    }
                    if (out != null && line.contains(mPID!!)) {
                        out!!.write((getDateEN() + "  " + line + "\n")
                                .toByteArray())
                    }
                }
            } catch (e: IOException) {
                eLogger(e.localizedMessage)
            } finally {
                if (mprocess != null) {
                    mprocess!!.destroy()
                    mprocess = null
                }
                if (mReader != null) {
                    mReader!!.close()
                    mReader = null
                }
                if (out != null) {
                    out!!.close()
                    out = null
                }
            }

        }

    }

}