package com.bs.demo.ui.adapter

import android.widget.CheckBox
import android.widget.ImageView
import com.bs.demo.R
import com.bs.demo.extension.loadCircle
import com.bs.demo.model.AnnBean
import com.bs.demo.model.UserInfoBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class UserListAdapter(data: List<UserInfoBean>?) : BaseQuickAdapter<UserInfoBean, BaseViewHolder>
        (R.layout.item_user, data) {

    override fun convert(helper: BaseViewHolder, item: UserInfoBean) {
        val logo=helper.getView<ImageView>(R.id.iv_item_logo)
        logo.loadCircle(item.logo)
        helper.setText(R.id.tv_item_title,item.name)
    }

}

