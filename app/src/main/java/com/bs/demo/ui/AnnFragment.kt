package com.bs.demo.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bs.demo.R
import com.bs.demo.common.base.BaseFragment
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.loadCircle
import com.bs.demo.extension.toBean
import com.bs.demo.extension.toBeans
import com.bs.demo.model.AnnBean
import com.bs.demo.model.UserInfoBean
import com.bs.demo.ui.adapter.MsgListAdapter
import com.bs.demo.utils.HttpUtil
import kotlinx.android.synthetic.main.fragment_ann.*

/**
 * 公告tab
 */
class AnnFragment : BaseFragment() {
    override fun initView() {

    }


    override fun onResume() {
        super.onResume()
        HttpUtil.newInstance()
            .post("getAnnList", object : HttpUtil.HttpListener{
                override fun onSuccess(t: String) {
                    val beans=t.toBeans(AnnBean::class.java)
                    rlv.adapter=MsgListAdapter(beans)
                }

                override fun onError(msg: String?) {
                    showToast(msg)
                }
            })

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ann, container, false)
    }
    companion object {
        fun newInstance() = AnnFragment().apply {}
    }

}
