package cn.white.ymc.todomaster.ui.add

import cn.white.ymc.todomaster.base.contract.BasePresenterImpl
import cn.white.ymc.todomaster.data.BaseResp
import cn.white.ymc.todomaster.data.TodoDetail
import cn.white.ymc.todomaster.model.api.ApiStore
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 添加todo p层
 *
 * @packageName: cn.white.ymc.todomaster.ui.add
 * @fileName: AddPresenter
 * @date: 2019/6/17  14:13
 * @author: ymc
 * @QQ:745612618
 */

class AddPresenter(var view:AddContract.View) : BasePresenterImpl<AddContract.View>(), AddContract.Presenter{

    override fun AddToDo(title: String, content: String, date: String, type: Int) {
        ApiStore.instances.add(title,content,date,type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BaseResp<TodoDetail>>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseResp<TodoDetail>) {
                        view.getAddToDoOk("添加成功")
                    }

                    override fun onError(e: Throwable) {
                        view.getAddToDoErr(e.message!!)
                    }

                })
    }

}