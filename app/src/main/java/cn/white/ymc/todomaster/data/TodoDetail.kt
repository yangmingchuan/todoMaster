package cn.white.ymc.todomaster.data

import java.io.Serializable

/**
 * 内容详情
 *
 * @author ymc
 * @date 2019年6月11日 11:26:46
 */
data class TodoDetail(
        val completeDateStr: String,
        val content: String,
        val date: Long,
        val dateStr: String,
        val id: Int,
        val priority : Int ,
        val status: Int,
        val title: String,
        val type: Int,
        val userId: Int
) : Serializable

