package com.bs.demo.widget.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import com.bs.demo.model.CommonListBean
import com.bs.demo.ui.adapter.BottomListAdapter
import com.chad.library.adapter.base.BaseQuickAdapter



class BottomListView : RecyclerView{
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }


    lateinit var bottomListAdapter: BottomListAdapter
    val list = mutableListOf<CommonListBean>()
    private fun initView() {
        clipChildren = false
        clipToPadding = false
        isFocusable=false
        isNestedScrollingEnabled = false
        overScrollMode = View.OVER_SCROLL_NEVER

        bottomListAdapter = BottomListAdapter(list)
        bottomListAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        adapter = bottomListAdapter

    }

    fun update() {
        bottomListAdapter.notifyDataSetChanged()
    }

    fun add(bean: CommonListBean) {
        list.add(bean)
    }

    fun addAll(bean: List<CommonListBean>) {
        list.addAll(bean)

    }

    fun clean() {
        list.clear()
    }

}
