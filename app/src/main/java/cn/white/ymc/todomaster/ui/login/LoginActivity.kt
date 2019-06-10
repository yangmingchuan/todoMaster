package cn.white.ymc.todomaster.ui.login

import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.base.BaseActivity
import cn.white.ymc.todomaster.data.UserBean
import cn.white.ymc.todomaster.ui.MainActivity
import cn.white.ymc.todomaster.utils.ConstantUtil
import cn.white.ymc.todomaster.utils.LoggerE
import cn.white.ymc.todomaster.utils.cache.PreferencesUtil
import cn.white.ymc.todomaster.utils.jump.JumpUtil
import cn.white.ymc.todomaster.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

/**
 *  登录界面
 *
 *  @author ymc
 *  @data 2018年9月27日 15:20:28
 */

class LoginActivity : BaseActivity(),LoginContract.View{
    /**
     *  sp 获取本地缓存是否有记录
     */
    private var login: Boolean by PreferencesUtil(ConstantUtil.ISLOGIN, false)
    private var username: String by PreferencesUtil(ConstantUtil.USERNAME, "")
    private var password: String by PreferencesUtil(ConstantUtil.PASSWORD, "")
    lateinit var presenter: LoginContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    /**
     * 加载数据
     */
    override fun initData() {
        presenter = LoginPresenter(this)
        if (login) {
            JumpUtil.overlay(this,MainActivity::class.java)
            finish()
        }
    }

    /**
     * 加载界面
     */
    override fun initView() {
        findViewById<Toolbar>(R.id.toolbar).title = "登 录"
        et_user.setText(username)
        et_password.setText(password)

        btn_login.setOnClickListener({
            if(checkEditText()){
                hideKeyboard()
                loginProgressBar.visibility = View.VISIBLE
                presenter.login(et_user.text.toString(),et_password.text.toString())
            }
        })

        btn_register.setOnClickListener({
            if(checkEditText()){
                hideKeyboard()
                loginProgressBar.visibility = View.VISIBLE
                presenter.register(et_user.text.toString(),et_password.text.toString())
            }
        })
    }

    override fun showLoading() {
    }

    override fun showEmpty() {
    }

    override fun showNormal() {
    }

    override fun showError(info: String) {

    }

    override fun loginOk(userInfo: UserBean) {
        loginProgressBar.visibility = View.GONE
        login = true
        JumpUtil.overlay(this,MainActivity::class.java)
        finish()
    }

    override fun reload() {
    }

    override fun loginErr(info: String) {
        loginProgressBar.visibility = View.GONE
        if ("账号密码不匹配！" == info) {
            til_user.error = info
            til_password.error = info
        } else {
            toast("登录失败:$info")
        }
    }

    override fun registerSuccess(userInfo: UserBean) {
        loginProgressBar.visibility = View.GONE
        JumpUtil.overlay(this,MainActivity::class.java)
        finish()
    }

    override fun registerErr(info: String) {
        loginProgressBar.visibility = View.GONE
        LoggerE(info)
    }

    /**
     * 检查 输入框内容
     */
    private fun checkEditText() : Boolean {
        // 是否取消
        var cancel = true
        et_user.error = null
        et_password.error = null
        val usernameText = et_user.text.toString().trim()
        val pwdText = et_password.text.toString().trim()
        if(TextUtils.isEmpty(usernameText)){
            et_user.error = getString(R.string.password_not_empty)
            return false
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
