package cn.white.ymc.todomaster.ui.main

import cn.white.ymc.todomaster.base.contract.BasePresenterImpl
import cn.white.ymc.todomaster.data.BaseResp
import cn.white.ymc.todomaster.model.api.ApiStore
import cn.white.ymc.todomaster.utils.ConstantUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @packageName: cn.white.ymc.todomaster.ui.main
 * @fileName: MainPresenter
 * @date: 2019/6/11  15:57
 * @author: ymc
 * @QQ:745612618
 */

class MainPresenter(var view : MainContract.View) :
        BasePresenterImpl<MainContract.View>(),MainContract.Presenter{

    /**
     * 获取
     */
    override fun getTodoList() {
    }


    /**
     * 删除todo
     */
    override fun delectTodo(id: Int) {
        ApiStore.instances.delete(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { object : Observer<BaseResp<Unit>>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(baseResp: BaseResp<Unit>) {
                        if (baseResp.errorCode == ConstantUtil.REQUEST_SUCCESS) {
                            view.delectTodoOk("删除成功")
                        } else if (baseResp.errorCode == ConstantUtil.REQUEST_ERROR) {
                            view.delectTodoErr(baseResp.errorMsg)
                        }
                    }

                    override fun onError(e: Throwable) {
                        view.delectTodoErr(e.message!!)
                    }

                } }
    }

}