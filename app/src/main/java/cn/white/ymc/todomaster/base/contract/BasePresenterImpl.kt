package cn.white.ymc.todomaster.base.contract

/**
 * base presenter 实现类
 *
 * @packageName: cn.white.ymc.todomaster.base.contract
 * @fileName: BasePresenterImpl
 * @date: 2018/12/17  16:58
 * @author: ymc
 * @QQ:745612618
 */

class BasePresenterImpl <T: BaseView> :BasePresenter<T>{

    private var mView: T? = null

    override fun attachView(view: T) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
    }

}