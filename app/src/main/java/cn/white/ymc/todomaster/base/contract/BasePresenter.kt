package cn.white.ymc.todomaster.base.contract

/**
 * base Presenter
 *
 * @packageName: cn.white.ymc.todomaster.base.contract
 * @fileName: BasePresenter
 * @date: 2018/12/17  16:56
 * @author: ymc
 * @QQ:745612618
 */
interface BasePresenter<T: BaseView> {

    /**
     * 注入View
     *
     * @param view view
     */
    abstract fun attachView(view: T)

    /**
     * 回收View
     */
    abstract fun detachView()

}