package com.bs.demo.ui.adapter

import android.widget.CheckBox
import android.widget.ImageView
import com.bs.demo.R
import com.bs.demo.model.AnnBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class MsgListAdapter(data: List<AnnBean>?) : BaseQuickAdapter<AnnBean, BaseViewHolder>
        (R.layout.item_msg, data) {

    override fun convert(helper: BaseViewHolder, item: AnnBean) {
        helper.setText(R.id.tv_item_title,item.title)
        helper.setText(R.id.tv_item_content,item.content)
        helper.setText(R.id.tv_item_date,item.date)
    }

}

