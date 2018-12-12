package cn.white.ymc.todomaster.ui.login

import cn.white.ymc.todomaster.base.contract.BaseView
import cn.white.ymc.todomaster.bean.LoginUserBean

/**
 *  登录契约类
 * @packageName: cn.white.ymc.todomaster.ui.login
 * @fileName: LoginContract
 * @date: 2018/12/7  11:24
 * @author: ymc
 * @QQ:745612618
 */

class LoginContract{

    interface View : BaseView {

        fun loginOk(userInfo: LoginUserBean)

        fun loginErr(info: String)

    }

}