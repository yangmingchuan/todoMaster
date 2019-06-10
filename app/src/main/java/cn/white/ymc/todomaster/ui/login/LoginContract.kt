package cn.white.ymc.todomaster.ui.login

import cn.white.ymc.todomaster.base.contract.BasePresenter
import cn.white.ymc.todomaster.base.contract.BaseView
import cn.white.ymc.todomaster.data.UserBean

/**
 *  登录契约类
 * @packageName: cn.white.ymc.todomaster.ui.login
 * @fileName: LoginContract
 * @date: 2018/12/7  11:24
 * @author: ymc
 * @QQ:745612618
 */

class LoginContract {

    interface View : BaseView {

        /**
         * 登录成功
         */
        fun loginOk(userInfo: UserBean)

        /**
         * 登录失败
         */
        fun loginErr(info: String)

        /**
         * 注册成功
         */
        fun registerSuccess(userInfo: UserBean)

        /**
         * 注册失败
         */
        fun registerErr(info: String)
    }

    interface Presenter : BasePresenter<View> {

        /**
         * 登录
         */
        fun login(name: String, password: String)

        /**
         * 注册
         */
        fun register(name: String, password: String)

    }

}