package cn.white.ymc.todomaster.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
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
    /**
     *  onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())
        appDavikManager.addActivity(this)
        initView()
        initData()
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
        super.onDestroy()
    }
}
