package cn.white.ymc.todomaster.ui.login

import cn.white.ymc.todomaster.base.contract.BasePresenterImpl
import cn.white.ymc.todomaster.data.BaseResp
import cn.white.ymc.todomaster.data.UserBean
import cn.white.ymc.todomaster.model.api.ApiStore
import cn.white.ymc.todomaster.utils.ConstantUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 登录 注册 界面 presenter
 *
 * @packageName: cn.white.ymc.todomaster.ui.login
 * @fileName: LoginPresenter
 * @date: 2019/6/10  15:20
 * @author: ymc
 * @QQ:745612618
 */

class LoginPresenter(var view: LoginContract.View) :
        BasePresenterImpl<LoginContract.View>(), LoginContract.Presenter{

    /**
     * 登录
     */
    override fun login(name: String, password: String) {
        ApiStore.instances.login(name,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BaseResp<UserBean>> {
                    override fun onNext(baseResp: BaseResp<UserBean>) {
                        if (baseResp.errorCode == ConstantUtil.REQUEST_SUCCESS) {
                            view.loginOk(baseResp.data!!)
                        } else if (baseResp.errorCode == ConstantUtil.REQUEST_ERROR) {
                            view.loginErr(baseResp.errorMsg)
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (e.message != null) {
                            view.loginErr(e.message!!)
                        }
                    }

                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                })
    }

    /**
     * 注册
     */
    override fun register(name: String, password: String) {
        ApiStore.instances.register(name,password,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { object :  Observer<BaseResp<UserBean>>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(baseResp: BaseResp<UserBean>) {
                        if (baseResp.errorCode == ConstantUtil.REQUEST_SUCCESS) {
                            view.registerSuccess(baseResp.data!!)
                        } else if (baseResp.errorCode == ConstantUtil.REQUEST_ERROR) {
                            view.registerErr(baseResp.errorMsg)
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (e.message != null) {
                            view.loginErr(e.message!!)
                        }
                    }

                } }
    }

}