package cn.white.ymc.todomaster.ui.update

import cn.white.ymc.todomaster.base.contract.BasePresenterImpl

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
    override fun unpdateToDo() {

    }

}