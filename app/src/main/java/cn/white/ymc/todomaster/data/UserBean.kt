package cn.white.ymc.todomaster.data

/**
 *  用户 实体类
 *
 *  data class 自动帮我们生成 getter setter equal copy toString 等方法
 *
 * @packageName: cn.white.ymc.todomaster.data
 * @fileName: UserBean
 * @date: 2018/9/28  16:33
 * @author: ymc
 * @QQ:745612618
 */

data class UserBean (
        var email : String ? ,
        var icon : String ? ,
        var id :Int ,
        val password : String ,
        val type : Int ,
        val username : String ,
        val collectIds : List<Int>
)