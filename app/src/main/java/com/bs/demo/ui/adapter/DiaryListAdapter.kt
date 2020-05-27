package com.bs.demo.ui.adapter

import com.bs.demo.R
import com.bs.demo.model.DiaryBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class DiaryListAdapter(
    data: List<DiaryBean> = emptyList(),
    private val onClick: ((DiaryBean) -> Unit)? = null,
    private val onLongClick: ((DiaryBean) -> Unit)? = null
) : BaseQuickAdapter<DiaryBean, BaseViewHolder>
    (R.layout.item_diary, data) {

    override fun convert(helper: BaseViewHolder, item: DiaryBean) {
        helper.setText(R.id.diary_title, item.title)
        helper.setText(R.id.diary_content, item.content)
        helper.itemView.setOnClickListener {
            onClick?.invoke(item)
        }
        helper.itemView.setOnLongClickListener {
            onLongClick?.invoke(item)
            false
        }
    }

}