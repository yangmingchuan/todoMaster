package cn.white.ymc.todomaster.ui

import android.graphics.drawable.Drawable
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.base.BaseActivity
import cn.white.ymc.todomaster.ui.login.LoginActivity
import cn.white.ymc.todomaster.ui.main.MainFragment
import cn.white.ymc.todomaster.utils.ConstantUtil
import cn.white.ymc.todomaster.utils.cache.PreferencesUtil
import cn.white.ymc.todomaster.utils.jump.JumpUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ViewTarget
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 首页
 *
 * @author ymc
 * @data 2018年9月20日 11:27:20
 */
class MainActivity : BaseActivity() {
    private lateinit var glide: ViewTarget<ImageView, Drawable>
    private val username: String by PreferencesUtil(ConstantUtil.USERNAME, "")
    var doneFragment: MainFragment? = null
    var notDoFragment: MainFragment? = null
    // 当前下标
    var currentIndexId = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView() {
        toolbar.run {
            setSupportActionBar(this)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }
        // Toolbar NavigationView 联动
        drawer_layout.run {
            val toggle = ActionBarDrawerToggle(
                    this@MainActivity,
                    this,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            )
            this.addDrawerListener(toggle)
            toggle.syncState()
        }

        // nav item  退出点击事件
        nav_view.run {
            getHeaderView(0).findViewById<TextView>(R.id.tv_nav_header).text = username
            setNavigationItemSelectedListener {
                when (it.itemId){
                    R.id.nav_exit ->{
                        PreferencesUtil.clear()
                        JumpUtil.overlay(this@MainActivity, LoginActivity::class.java)
                        finish()
                    }
                }
                return@setNavigationItemSelectedListener true
            }
        }

        // 显示 侧滑头像
        glide = Glide
                .with(this)
                .load(ConstantUtil.NAV_HEADER_IMG)
                .apply(RequestOptions.bitmapTransform(CircleCrop())) // 圆形处理
                .into(nav_view.getHeaderView(0).findViewById(R.id.iv_nav_header))

        bottom_navigation_view.run {
            setOnNavigationItemSelectedListener {
                setFragment(it.itemId)
                return@setOnNavigationItemSelectedListener when(it.itemId){
                    R.id.navigation_home -> {
                        currentIndexId = R.id.navigation_home
                        spinner.setSelection(doneFragment?.currentType ?: ConstantUtil.TYPE_ONE)
                        true
                    }
                    R.id.navigation_dashboard -> {
                        currentIndexId = R.id.navigation_dashboard
                        spinner.setSelection(notDoFragment?.currentType ?: ConstantUtil.TYPE_ONE)
                        true
                    }
                    else -> false
                }
            }
            // 默认选中 首页
            selectedItemId = R.id.navigation_home
        }

        // spinner 点击事件
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (currentIndexId) {
                    R.id.navigation_home -> {
                        doneFragment?.run {
                            if (currentType != p2) {
                                currentType = p2
                                loadData()
                            }
                        }
                    }
                    R.id.navigation_dashboard -> {
                        notDoFragment?.run {
                            if (currentType != p2) {
                                currentType = p2
                                loadData()
                            }
                        }
                    }
                }
            }

        }

    }

    /**
     * 设置 fragment
     */
    private fun setFragment(index: Int) {
        supportFragmentManager.beginTransaction().apply {
            // 判断是否初始化
            doneFragment ?: run {
                MainFragment.newInstance(true).run {
                    doneFragment = this
                    add(R.id.frame_content, this)
                }
            }
            notDoFragment ?: run {
                MainFragment.newInstance(false).run {
                    notDoFragment = this
                    add(R.id.frame_content, this)
                }
            }
            hideFragment(this)
            when (index) {
                R.id.navigation_home -> {
                    doneFragment?.run {
                        show(this)
                    }
                }
                R.id.navigation_dashboard -> {
                    notDoFragment?.run {
                        show(this)
                    }
                }
            }
        }.commit()
    }

    private fun hideFragment(fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.hide(doneFragment)
        fragmentTransaction.hide(notDoFragment)
    }

}
