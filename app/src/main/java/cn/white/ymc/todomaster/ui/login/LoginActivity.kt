package cn.white.ymc.todomaster.ui.login

import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.base.BaseActivity
import cn.white.ymc.todomaster.utils.cache.LogcatHelper

/**
 *  登录界面
 *
 *  @author ymc
 *  @data 2018年9月27日 15:20:28
 */

class LoginActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    /**
     * 加载数据
     */
    override fun initData() {

    }

    /**
     * 加载界面
     */
    override fun initView() {

    }

    /**
     * 登录按钮
     */
    fun login(){

    }

    /**
     * 跳转注册
     */
    fun toRegister(){

    }

    override fun onDestroy() {
        LogcatHelper(this).stopLogThread()
        super.onDestroy()
    }

}
