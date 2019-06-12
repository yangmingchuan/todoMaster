package cn.white.ymc.todomaster.data

/**
 * 列表数据集合
 */
data class ListResponse(
        val curPage: Int,
        val datas: List<TodoDetail>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)