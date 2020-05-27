package com.bs.demo.widget

import android.view.Gravity
import android.view.View
import com.bs.demo.R
import com.bs.demo.common.base.BaseDialog
import com.bs.demo.extension.addDivider
import com.bs.demo.model.CommonListBean

import kotlinx.android.synthetic.main.dialog_bottom_list.*

class BottomListDialog : BaseDialog() {
    override fun getLayout(): Int {
        return R.layout.dialog_bottom_list
    }

    var title = ""
    private val list = mutableListOf<CommonListBean>()

    interface CallBack {
        fun onItemClick(postion: Int, title: String)
    }

    private lateinit var callBack: CallBack

    fun setCallBack(callBack: CallBack):BottomListDialog{
        this.callBack=callBack
        return this
    }
    override fun initView(view: View) {
        tv_dialog_bottom_list_title.text = title
        rlv_dialog_bottom_list.clean()
        rlv_dialog_bottom_list.addAll(list)
        rlv_dialog_bottom_list.update()
        rlv_dialog_bottom_list.addDivider(R.color.color_F4F4F4,true)
        rlv_dialog_bottom_list.bottomListAdapter.setOnItemClickListener { adapter, view, position ->
            val bean=list[position]
            bean?.let {
                callBack.onItemClick(rlv_dialog_bottom_list.list.indexOf(it), it.title)
            }
            dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        window!!.setGravity(Gravity.BOTTOM)
        window.setWindowAnimations(R.style.bottom_dialog)

    }

    fun addData(t: String): BottomListDialog {
        return addData(t, false)
    }

    fun addData(t: String, isCheck: Boolean): BottomListDialog {
        list.add(CommonListBean().apply {
            this.title = t
            this.isCheck = isCheck
        })
        return this

    }

    companion object {
        fun newInstance() = BottomListDialog().apply {

        }
    }

}