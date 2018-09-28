package cn.white.ymc.todomaster.ui

import android.support.design.widget.BottomNavigationView
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 首页
 *
 * @author ymc
 * @data 2018年9月20日 11:27:20
 */
class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    /**
     * 底部按钮点击事件
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}