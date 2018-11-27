package cn.white.ymc.todomaster.base

import android.app.Application
import cn.white.ymc.todomaster.utils.cache.CrashHandler
import cn.white.ymc.todomaster.utils.cache.LogcatHelper
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary

/**
 * application
 *
 * @packageName: cn.white.ymc.todomaster.base
 * @fileName: MApplication
 * @date: 2018/9/19  11:19
 * @author: ymc
 * @QQ:745612618
 */

class MApplication : Application() {

    /**
     * 延迟 单例 初始化
     */
    companion object {
        val instance : MApplication by lazy {
            MApplication()
        }
    }

    override fun onCreate() {
        super.onCreate()
        /**
         *  logger 初始化
         */
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(3)
                .methodOffset(5)
                .tag("todo")
                .build()
        Logger.addLogAdapter( AndroidLogAdapter(formatStrategy) )

        /**
         * 打开log 持久化
         */
        LogcatHelper(this).instance.startLogThread()

        /**
         *  crash 本地持久化
         */
        CrashHandler(this).instance.initCarsh()

        /**
         * LeakCanary初始化
         */
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

    }



}
