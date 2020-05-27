package com.bs.demo.ui.adapter

import com.bs.demo.R
import com.bs.demo.model.CommonListBean
import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder



class BottomListAdapter(data: List<CommonListBean>?) :
    BaseItemDraggableAdapter<CommonListBean, BaseViewHolder>
        (R.layout.item_bottom_list, data) {
    override fun convert(helper: BaseViewHolder, item: CommonListBean) {
        helper.setText(R.id.tv_item_title, item.title)
        if (item.isCheck){
            helper.setTextColor(R.id.tv_item_title,mContext.resources.getColor(R.color.color_1869B0))
        }else{
            helper.setTextColor(R.id.tv_item_title,mContext.resources.getColor(R.color.color_2D2D2D))
        }
    }
}
