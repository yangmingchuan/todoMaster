package cn.white.ymc.todomaster.ui.update

import cn.white.ymc.todomaster.base.contract.BasePresenterImpl
import cn.white.ymc.todomaster.data.BaseResp
import cn.white.ymc.todomaster.data.TodoDetail
import cn.white.ymc.todomaster.model.api.ApiStore
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 更新todo p层
 *
 * @packageName: cn.white.ymc.todomaster.ui.update
 * @fileName: UpdatePresenter
 * @date: 2019/6/17  16:35
 * @author: ymc
 * @QQ:745612618
 */

class UpdatePresenter(var view : UpdateContract.View):
        BasePresenterImpl<UpdateContract.View>(),UpdateContract.Presenter{


    /**
     * 更新内容
     */
    override fun updateToDo(id: Int, title: String, content: String,
                             date: String, status: Int, type: Int) {
        ApiStore.instances.update(id,title,content,date,status,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BaseResp<TodoDetail>>{
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseResp<TodoDetail>) {
                        view.updateOk("更新成功")
                    }

                    override fun onError(e: Throwable) {
                        view.updateErr("更新失败")
                    }

                    override fun onComplete() {
                    }

                })
    }
}