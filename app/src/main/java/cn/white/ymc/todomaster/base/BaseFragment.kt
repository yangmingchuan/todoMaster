package cn.white.ymc.todomaster.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.white.ymc.todomaster.base.contract.BaseView

/**
 * 基础 Fragment类
 *
 * @packageName: cn.white.ymc.todomaster.base
 * @fileName: BaseFragment
 * @date: 2018/9/27  13:37
 * @author: ymc
 * @QQ:745612618
 */

abstract class BaseFragment : Fragment() ,BaseView{

    /**
     * 处理页面加载中、页面加载失败、页面没数据
     */
    private val NORMAL_STATE = 0X0001
    private val LOADING_STATE = 0X0002
    private val ERROR_STATE = 0X0003
    private val EMPTY_STATE = 0X0004

    val fragment = this
    var viewGroup : ViewGroup? = null

    /**
     * onCreateView
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutID(), container, false)
    }

    /**
     * onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initData()
    }

    /**
     * 界面初始化
     */
    public open fun initUI() {
        if(view == null){
            return
        }
//        viewGroup = view!!.findViewById(R.id.normal_view)

    }

    abstract fun initData()


    /**
     *  获取layout id信息
     */
    abstract fun getLayoutID(): Int

    /**
     * 显示正常界面
     */
    override fun showNormal() {

    }

    /**
     * 显示空
     */
    override fun showEmpty() {

    }

    /**
     * 显示错误
     */
    override fun showError(err: String) {

    }

    /**
     * 显示正在加载
     */
    override fun showLoading() {

    }

    override fun reload() {

    }
}
