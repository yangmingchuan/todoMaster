package cn.white.ymc.todomaster.ui.login

import android.text.TextUtils
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

    /**
     * 取消请求
     */
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
     * 登录事件
     */
    fun login(){
        if(checkEditText()){

        }
    }

    /**
     * 注册事件
     */
    fun register(){
        if(checkEditText()){

        }
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

    public override fun onDestroy() {
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
        if(TextUtils.isEmpty(usernameText)){
            et_user.error = getString(R.string.password_not_empty)
        }
        if(TextUtils.isEmpty(pwdText)){
            et_password.error = getString(R.string.password_not_empty)
            cancel = false
        }else if(pwdText.length < 6){
            et_password.error = getString(R.string.password_length_short)
            cancel = false
        }
        return cancel

    }

}
