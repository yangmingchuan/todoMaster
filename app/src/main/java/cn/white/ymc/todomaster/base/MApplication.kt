package cn.white.ymc.todomaster.base

import android.Manifest
import android.app.Application
import cn.white.ymc.todomaster.utils.cache.CrashHandler
import cn.white.ymc.todomaster.utils.cache.LogcatHelper
import cn.white.ymc.todomaster.utils.cache.PreferencesUtil
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.yanzhenjie.permission.AndPermission

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

    private val sContext = this

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
        requestPermission()
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
        LogcatHelper(this).startLogThread()

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

        PreferencesUtil.attch(this)
    }

    /**
     * 动态申请权限
     */
    private fun requestPermission() {
        AndPermission.with(this)
                .permission(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                // 准备方法，和 okhttp 的拦截器一样，在请求权限之前先运行改方法，已经拥有权限不会触发该方法
                .rationale({ context, permissions, executor -> executor.execute() })
                .onDenied({ permissions ->
                    if (AndPermission.hasAlwaysDeniedPermission(sContext, permissions)) {
                        // 打开权限设置页
                        AndPermission.permissionSetting(sContext).execute()
                    }
                }).start()
    }

}
