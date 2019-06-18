package cn.white.ymc.todomaster.ui.update

import android.app.DatePickerDialog
import android.support.v7.widget.Toolbar
import android.view.View
import cn.white.ymc.todomaster.R
import cn.white.ymc.todomaster.base.BaseActivity
import cn.white.ymc.todomaster.data.TodoDetail
import cn.white.ymc.todomaster.utils.ConstantUtil
import cn.white.ymc.todomaster.utils.toast
import kotlinx.android.synthetic.main.activity_update.*
import java.util.*

/**
 * 更新内容界面
 *
 * @author ymc
 * @date 2019年6月18日 09:59:38
 */

class UpdateActivity : BaseActivity(), UpdateContract.View{

    private lateinit var todoDetail: TodoDetail
    private lateinit var presenter: UpdateContract.Presenter

    override fun getLayoutId(): Int = R.layout.activity_update

    override fun initView() {
        todoDetail = intent.getSerializableExtra(ConstantUtil.INTENT_NAME_TODODETAIL) as TodoDetail
        presenter = UpdatePresenter(this)
        findViewById<Toolbar>(R.id.toolbar_update).run {
            setSupportActionBar(this)
        }

        supportActionBar?.run {
            // 设置返回按钮
            setDisplayHomeAsUpEnabled(true)
            title = "更新Todo"
        }

        tv_date.run {
            text = todoDetail.dateStr
            setOnClickListener {
                setDate()
            }
        }

        fab_update.run {
            setOnClickListener {
                if (checkContent()) {
                    progressBar.visibility = View.VISIBLE
                    presenter.updateToDo(todoDetail.id, et_title.text.toString(), et_content.text.toString(), tv_date.text.toString(),
                            todoDetail.status, todoDetail.type)
                }
            }
        }

        et_title.setText(todoDetail.title)
        et_content.setText(todoDetail.content)
    }

    override fun initData() {

    }

    /**
     * 设置时间
     */
    private fun setDate() {
        DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    val monthVal =
                            if ((month + 1) < 10)
                                "0${month + 1}"
                            else
                                "${month + 1}"
                    val dayVal =
                            if (day < 10)
                                "0$day"
                            else
                                "$day"

                    tv_date.text = "$year-$monthVal-$dayVal"
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).run {
            datePicker.minDate = Date().time
            show()
        }

    }

    /**
     * 检查内容
     */
    private fun checkContent(): Boolean {
        return if (et_title.text.toString().isEmpty()) {
            til_title.error = "标题不能为空"
            false
        }else true
    }

    override fun showNormal() {
    }

    override fun showError(err: String) {
    }

    /**
     * 更新成功 / 失败
     */
    override fun updateOk(info: String) {
        activity.toast(info)
        progressBar.visibility = View.GONE
        setResult(ConstantUtil.MAIN_UPDATE_REQUEST_CODE)
        finish()
    }

    override fun updateErr(err: String) {
        progressBar.visibility = View.GONE
        activity.toast(err)
    }

    override fun showLoading() {
    }

    override fun showEmpty() {
    }

    override fun reload() {
    }
}
