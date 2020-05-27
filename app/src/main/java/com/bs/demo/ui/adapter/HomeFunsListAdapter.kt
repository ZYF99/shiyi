package com.bs.demo.ui.adapter

import android.widget.CheckBox
import android.widget.ImageView
import com.bs.demo.R
import com.bs.demo.model.AnnBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class HomeFunsListAdapter(data: List<String>?) : BaseQuickAdapter<String, BaseViewHolder>
        (R.layout.item_home_fun, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.tv_item_title,item)

    }

}

