package cn.white.ymc.todomaster.ui.main

import cn.white.ymc.todomaster.base.contract.BasePresenter
import cn.white.ymc.todomaster.base.contract.BaseView
import cn.white.ymc.todomaster.data.AllListResponse

/**
 * 首页 契约类
 *
 * @packageName: cn.white.ymc.todomaster.ui.main
 * @fileName: MainContract
 * @date: 2019/6/11  15:47
 * @author: ymc
 * @QQ:745612618
 */

class MainContract{

    interface View : BaseView{

        /**
         * 删除是否成功
         */
        fun delectTodoOk(msg:String)

        fun delectTodoErr(errMsg: String)

        /**
         * 获取 数据列表 是否成功
         */
        fun getListOk(response : AllListResponse)

        fun getListErr(errMsg : String)
    }


    interface Presenter : BasePresenter<View>{

        /**
         * 删除 todo
         */
        fun delectTodo(id : Int)

        /**
         * 获取todo 列表
         */
        fun getTodoList()

    }


}