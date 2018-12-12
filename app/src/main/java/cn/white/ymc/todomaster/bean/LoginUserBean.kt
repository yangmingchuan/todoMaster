package cn.white.ymc.todomaster.bean

/**
 * @packageName: cn.white.ymc.todomaster.bean
 * @fileName: UserInfo
 * @date: 2018/12/12  15:41
 * @author: ymc
 * @QQ:745612618
 */


data class LoginUserBean(
    val data: Data,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val chapterTops: List<Any>,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val password: String,
    val token: String,
    val type: Int,
    val username: String
)