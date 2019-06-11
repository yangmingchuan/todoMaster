package cn.white.ymc.todomaster.data

/**
 * 列表数据
 *
 * @author ymc
 * @data 2019年6月11日 16:23:57
 */
data class AllListResponse(
        val type: Int,
        val doneList: List<DoneOrTodoList>,
        val todoList: List<DoneOrTodoList>
) {
    data class DoneOrTodoList(
            val date: Long,
            val todoList: List<TodoDetail>
    )
}