package cn.white.ymc.todomaster.ui.main

import android.arch.lifecycle.Lifecycle
import android.graphics.Canvas
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.data.ListResponse
import cn.white.ymc.todomaster.data.TodoDetail
import cn.white.ymc.todomaster.utils.ConstantUtil
import cn.white.ymc.todomaster.utils.TodoAdapter
import cn.white.ymc.todomaster.utils.toast
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import kotlinx.android.synthetic.main.fragment.*

/**
 * 碎片界面
 * 共用 于 已完成/ 未完成
 *
 * @packageName: cn.white.ymc.todomaster.ui
 * @fileName: MainFragment
 * @date: 2019/6/11  11:19
 * @author: ymc
 * @QQ:745612618
 */

class MainFragment : Fragment(),MainContract.View{

    // 是否 完成 状态
    private var DONE = false

    var currentPage = 1                // 当前页，从1开始
    var currentType = ConstantUtil.TYPE_ONE    // 当前类型
    var isOver = true                  // 是否结束（没有下一页）

    val data = ArrayList<TodoDetail>()
    var delectIndex = -1
    private val adapter: TodoAdapter by lazy {
        TodoAdapter(data)
    }

    lateinit var presenter : MainContract.Presenter

    companion object {

        fun newInstance(done: Boolean): MainFragment {
            val bundle = Bundle()
            bundle.putBoolean(ConstantUtil.BUNDLE_KEY_DONE, done)
            return MainFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DONE = arguments?.getBoolean(ConstantUtil.BUNDLE_KEY_DONE) ?: false
        presenter = MainPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@MainFragment.adapter
        }

        adapter.run {
            bindToRecyclerView(rv)
            setEmptyView(R.layout.fragment_empty,rv)

            ItemDragAndSwipeCallback(this).run {
                setSwipeMoveFlags(ItemTouchHelper.START)
                ItemTouchHelper(this).attachToRecyclerView(rv)
            }

            // 开启侧滑 并打开侧滑方法监听
            enableSwipeItem()
            setOnItemSwipeListener(onItemSwipeListener)

            swipe_Refresh.setOnRefreshListener {
                loadData()
            }
        }

        loadData()
    }


    /**
     * 加载数据
     */
    fun loadData(){
        currentPage = 1
        if (DONE) {
            presenter.getTodoList(currentType,currentPage)
        } else {
            presenter.getUnTodoList(currentType,currentPage)
        }
    }



    /**
     * 侧滑方法监听
     */
    private val onItemSwipeListener = object : OnItemSwipeListener{
        override fun clearView(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
        }

        override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
            delectIndex = pos
            val item = adapter.data[pos]
            presenter.delectTodo(item.id)
        }

        override fun onItemSwipeStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
        }

        override fun onItemSwipeMoving(canvas: Canvas?, viewHolder: RecyclerView.ViewHolder?,
                                       dX: Float, dY: Float, isCurrentlyActive: Boolean) {
        }
    }


    override fun showNormal() {
    }

    override fun showError(err: String) {
    }

    override fun showLoading() {
    }

    override fun showEmpty() {
    }

    /**
     * 删除 成功/ 失败
     */
    override fun delectTodoOk(msg: String) {
        activity!!.toast(msg)
        // 刷新后一条数据
        if (adapter.data.size > 1 && delectIndex >0) {
            adapter.notifyItemChanged(delectIndex + 1)
        }
    }

    override fun delectTodoErr(errMsg: String) {
        activity!!.toast(errMsg)
    }

    override fun reload() {
    }

    /**
     * 获取数据 成功/失败
     */
    override fun getListOk(response: ListResponse) {
        //设置可刷新
        if (swipe_Refresh.isRefreshing) swipe_Refresh.isRefreshing = false
        swipe_Refresh.isRefreshing = true
        adapter.setEnableLoadMore(false)
        adapter.run {
            replaceData(response.datas)
            isOver = response.over
            if (!isOver) {
                setEnableLoadMore(true)
                currentPage++
            }
        }
    }

    override fun getListErr(errMsg: String) {

    }


}