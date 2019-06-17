package cn.white.ymc.todomaster.ui.add

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.base.BaseActivity
import cn.white.ymc.todomaster.utils.ConstantUtil
import cn.white.ymc.todomaster.utils.getDateEN
import cn.white.ymc.todomaster.utils.getDateTime
import cn.white.ymc.todomaster.utils.toast
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

/**
 *  添加界面
 *
 *  @author ymc
 *  @date 2019年6月17日 14:15:06
 */
class AddActivity : BaseActivity(),AddContract.View {

    /**
     * 当前状态
     */
    private var currentType = ConstantUtil.TYPE_ONE

    private lateinit var presenter : AddContract.Presenter

    override fun getLayoutId(): Int = R.layout.activity_add

    override fun initView() {
        findViewById<Toolbar>(R.id.toolbar_add).run {
            setSupportActionBar(this)
        }
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = "添加"
        }
        tv_date.run {
            text = getDateTime()
            setOnClickListener {
                setDate()
            }
        }
        fab_add.run {
            setOnClickListener {
                if (checkContent()) {
                    progressBar.visibility = View.VISIBLE

                    presenter.AddToDo(et_title.text.toString(), et_content.text.toString(),
                            tv_date.text.toString(), currentType)
                }
            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                currentType = p2
            }
        }
    }

    /**
     * 设置时间
     */
    private fun setDate() {
        DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, var2, var3, var4 ->
                    val month = if ((var3 + 1) < 10)
                                "0${var3 + 1}"
                            else
                                "${var3 + 1}"

                    val day = if (var4 < 10)
                                "0$var4"
                            else
                                "$var4"
                    tv_date.text = "$var2-$month-$day"
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).run {
            //设置最小时间
            datePicker.minDate = Date().time
            show()
        }
    }

    override fun initData() {
        presenter = AddPresenter(this)
    }

    /**
     * 检查内容
     */
    private fun checkContent(): Boolean {
        til_title.error = null
        til_content.error = null
        var cancel = false
        var focusView: View? = null
        val title = et_title.text.toString()

        if (title.isEmpty()) {
            til_title.error = "标题不能为空"
            focusView = til_title
            cancel = true
        }

        return if (cancel) {
            focusView?.requestFocus()
            false
        } else {
            true
        }
    }

    override fun showNormal() {
    }

    override fun showError(err: String) {
    }

    override fun getAddToDoOk(info: String) {
        toast(info)
        progressBar.visibility = View.GONE

        setResult(ConstantUtil.MAIN_ADD_REQUEST_CODE,
                Intent().putExtra(ConstantUtil.INTENT_NAME_TYPE, currentType))
        finish()
    }

    override fun getAddToDoErr(err: String) {
        progressBar.visibility = View.GONE
        toast(err)
    }

    override fun showLoading() {
    }

    override fun showEmpty() {

    }

    override fun reload() {
    }

}
