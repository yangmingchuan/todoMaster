package cn.white.ymc.todomaster.ui.main

import cn.white.ymc.todomaster.base.contract.BasePresenterImpl
import cn.white.ymc.todomaster.data.BaseResp
import cn.white.ymc.todomaster.data.ListResponse
import cn.white.ymc.todomaster.model.api.ApiStore
import cn.white.ymc.todomaster.utils.ConstantUtil
import cn.white.ymc.todomaster.utils.LoggerE
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 首页 p层
 *
 * @packageName: cn.white.ymc.todomaster.ui.main
 * @fileName: MainPresenter
 * @date: 2019/6/11  15:57
 * @author: ymc
 * @QQ:745612618
 */

class MainPresenter(var view : MainContract.View) :
        BasePresenterImpl<MainContract.View>(),MainContract.Presenter{

    /**
     * 分页获取todo 数据
     */
    override fun getTodoList(type: Int , page: Int) {
        ApiStore.instances.listDone(type,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BaseResp<ListResponse>>{
                    override fun onSubscribe(d: Disposable) {
                        LoggerE("onSubscribe")
                    }

                    override fun onError(e: Throwable) {
                        LoggerE("onError")
                        view.getListErr(e.message!!)
                    }

                    override fun onNext(t: BaseResp<ListResponse>) {
                        LoggerE("onNext")
                        if(t.errorCode == ConstantUtil.REQUEST_SUCCESS){
                            view.getListOk(t.data!!)
                        }else if(t.errorCode==ConstantUtil.REQUEST_ERROR){
                            view.getListErr(t.errorMsg)
                        }
                    }

                    override fun onComplete() {
                        LoggerE("onComplete")
                    }

                })
    }

    /**
     * 分页获取 未完成todo数据
     */
    override fun getUnTodoList(type: Int, page: Int) {
        ApiStore.instances.listNotDo(type,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { object : Observer<BaseResp<ListResponse>>{
                    override fun onComplete() {
                        LoggerE("e")
                    }

                    override fun onSubscribe(d: Disposable) {
                        LoggerE(d.toString())
                    }

                    override fun onNext(t: BaseResp<ListResponse>) {
                        if(t.errorCode == ConstantUtil.REQUEST_SUCCESS){
                            view.getListOk(t.data!!)
                        }else if(t.errorCode==ConstantUtil.REQUEST_ERROR){
                            view.getListErr(t.errorMsg)
                        }
                    }

                    override fun onError(e: Throwable) {
                        view.getListErr(e.message!!)
                    }

                } }

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