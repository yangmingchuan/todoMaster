package cn.white.ymc.todomaster.utils

import android.view.View
import android.widget.TextView
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.data.TodoDetail
import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 列表适配器
 *
 * @packageName: cn.white.ymc.todomaster.utils
 * @fileName: TodoAdapter
 * @date: 2019/6/11  15:11
 * @author: ymc
 * @QQ:745612618
 */

class TodoAdapter(data: List<TodoDetail>) :
        BaseItemDraggableAdapter<TodoDetail, BaseViewHolder>(R.layout.item, data) {

    override fun convert(helper: BaseViewHolder, item: TodoDetail?) {
        item ?: return
        helper.setText(R.id.tv_item_date, item.dateStr)
                .setText(R.id.tv_item_title, item.title)
                .setText(R.id.tv_item_content, item.content)
                .setText(R.id.tv_item_complete_date, "完成时间：${item.completeDateStr}")

        helper.getView<TextView>(R.id.tv_item_complete_date).visibility =
                if (item.dateStr.isEmpty()) {
                    View.GONE
                } else {
                    View.VISIBLE
                }

        // 当相邻两条记录为同一天时，隐藏后一条记录的日期
        helper.getView<TextView>(R.id.tv_item_date).visibility = if (helper.layoutPosition > 0) {
            if (item.dateStr == data[helper.layoutPosition - 1].dateStr) {
                View.GONE
            } else {
                View.VISIBLE
            }
        } else {
            View.VISIBLE
        }


    }

}