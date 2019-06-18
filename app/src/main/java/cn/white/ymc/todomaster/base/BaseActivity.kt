package cn.white.ymc.todomaster.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
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
//        取消请求
//        cancelRequest()
        super.onDestroy()
    }

    /**
     * 重写 界面跳转
     * 进行重复点击检测
     * [eg] startActivity 通过源码 最后也会调用 startActivityForResult
     */
    @SuppressLint("RestrictedApi")
    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        if (startActivitySelfCheck(intent!!)) {
            super.startActivityForResult(intent, requestCode, options);
        }
    }

    /**
     * 添加 重复点击时间判断
     *
     */
    private var mActivityJumpTag: String? = null
    // 跳转事件
    private var mActivityJumpTime: Long = 0

    /**
     * 判断Avtiviy 是否重复跳转
     */
    private fun startActivitySelfCheck(intent : Intent):Boolean{
        // 默认检查通过
        var result = true
        // 标记对象
        val tag: String?
        if (intent.component != null) { // 显式跳转
            tag = intent.component.className
        } else if (intent.action != null) { // 隐式跳转
            tag = intent.action
        } else {
            return result
        }
        if (tag == mActivityJumpTag && mActivityJumpTime >= SystemClock.uptimeMillis() - 500) {
            result = false
        }
        // 更新记录启动标记和时间
        mActivityJumpTag = tag
        mActivityJumpTime = SystemClock.uptimeMillis()
        return result
    }

    /**
     * 隐藏键盘
     */
    protected fun hideKeyboard() {
        currentFocus?.let {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 禁止交互
     */
    protected fun disableInteraction() {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    /**
     * 允许交互
     */
    protected fun enableInteraction() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
