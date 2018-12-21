package cn.white.ymc.todomaster.ui.login

import android.view.View
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.base.BaseActivity
import cn.white.ymc.todomaster.bean.LoginUserBean
import kotlinx.android.synthetic.main.activity_login.*

/**
 *  登录界面
 *
 *  @author ymc
 *  @data 2018年9月27日 15:20:28
 */

class LoginActivity : BaseActivity(),LoginContract.View{

    override fun cancelRequest() {
    }

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
     * 注册
     */
    fun register(){

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

    /**
     * 检查 输入框内容
     */
    private fun checkEditText() : Boolean {
        // 是否取消
        var cancel = false
        // 选中图片
        var focusView: View? = null
        et_user.error = null
        et_password.error = null
        val usernameText = et_user.text.toString().trim()
        val pwdText = et_password.text.toString().trim()
        return true

    }

}
