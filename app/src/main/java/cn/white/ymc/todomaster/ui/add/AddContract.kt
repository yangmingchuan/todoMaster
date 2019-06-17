package cn.white.ymc.todomaster.ui.add

import cn.white.ymc.todomaster.base.contract.BasePresenter
import cn.white.ymc.todomaster.base.contract.BaseView

/**
 * 添加界面 契约类
 *
 * @packageName: cn.white.ymc.todomaster.ui.add
 * @fileName: AddContract
 * @date: 2019/6/17  13:49
 * @author: ymc
 * @QQ:745612618
 */

class AddContract{

    interface View : BaseView{

        fun getAddToDoOk(info:String)

        fun getAddToDoErr(err:String)

    }

    interface Presenter : BasePresenter<View>{

        fun AddToDo(title:String,content:String,date:String ,type:Int)

    }


}