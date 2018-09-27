package cn.white.ymc.todomaster.base.contract

/**
 * 基础 view 接口
 *
 * @packageName: cn.white.ymc.todomaster.base.contract
 * @fileName: BaseView
 * @date: 2018/9/27  13:56
 * @author: ymc
 * @QQ:745612618
 */

interface BaseView{
    /**
     * 正常界面
     */
    abstract fun showNormal()

    /**
     * 错误界面
     */
    abstract fun showError(err: String)

    /**
     * 正在加载
     */
    abstract fun showLoading()

    /**
     * Show empty
     */
    abstract fun showEmpty()

    /**
     * Reload
     */
    abstract fun reload()
}



