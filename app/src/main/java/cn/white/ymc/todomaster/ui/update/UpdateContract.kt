package cn.white.ymc.todomaster.ui.update

import cn.white.ymc.todomaster.base.contract.BasePresenter
import cn.white.ymc.todomaster.base.contract.BaseView
import retrofit2.http.Field
import retrofit2.http.Path

/**
 * 更新todo 契约类
 *
 * @packageName: cn.white.ymc.todomaster.ui.update
 * @fileName: UpdateContract
 * @date: 2019/6/17  16:32
 * @author: ymc
 * @QQ:745612618
 */

class UpdateContract{

    interface View : BaseView{

        fun updateOk(info:String)

        fun updateErr(err:String)
    }

    interface Presenter : BasePresenter<View>{

        /**
         * 更新todo 信息
         */
        fun updateToDo(id: Int,title: String, content: String,date: String, status: Int, type: Int)

    }

}