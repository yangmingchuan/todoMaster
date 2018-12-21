package cn.white.ymc.todomaster.base

import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.utils.davik.AppDavikActivityUtil

/**
 *  基类  activity
 *
 * @packageName: cn.white.ymc.todomaster.base
 * @fileName: BaseActivity
 * @date: 2018/9/27  11:24
 * @author: ymc
 * @QQ:745612618
 */

abstract class BaseActivity : AppCompatActivity() {
    /**
     * activity 管理工具
     */
    private val appDavikManager = AppDavikActivityUtil.instance
    var activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        appDavikManager.addActivity(this)
        initStatusColor()
        initView()
        initData()
    }

    /**
     * 设置 透明状态栏
     */
    private fun initStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = activity.window
            //设置透明状态栏,这样才能让 ContentView 向上  6.0小米手机设置 tootlbar 会被挤上去
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //设置状态栏颜色
            window.statusBarColor = getColor(R.color.theme)
            val mContentView = activity.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
            val mChildView = mContentView.getChildAt(0)
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
                ViewCompat.setFitsSystemWindows(mChildView, false)
            }
        }

    }

    /**
     * cancel request
     */
    protected abstract fun cancelRequest()

    /**
     *  获取 布局id
     */
    abstract fun getLayoutId(): Int

    /**
     * 数据初始化
     */
    abstract fun initData()

    /**
     * 界面初始化
     */
    abstract fun initView()

    /**
     * onDestroy
     */
    override fun onDestroy() {
        appDavikManager.removeActivity(this)
        cancelRequest()
        super.onDestroy()
    }
}
