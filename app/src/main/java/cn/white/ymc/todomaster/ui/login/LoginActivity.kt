package cn.white.ymc.todomaster.ui.login

import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.base.BaseActivity
import cn.white.ymc.todomaster.bean.LoginUserBean

/**
 *  登录界面
 *
 *  @author ymc
 *  @data 2018年9月27日 15:20:28
 */

class LoginActivity : BaseActivity(),LoginContract.View{

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

    override fun showLoading() {
    }

    override fun showEmpty() {
    }

    override fun showNormal() {
    }

    override fun showError(err: String) {
    }

    override fun loginOk(userInfo: LoginUserBean) {
    }

    override fun reload() {
    }

    override fun loginErr(info: String) {
    }

    override fun registerSuccess(userInfo: LoginUserBean) {
    }

    override fun registerErr(info: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
