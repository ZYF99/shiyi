package com.bs.demo.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.base.BaseFragment
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.loadCircle
import com.bs.demo.extension.toBean
import com.bs.demo.model.UserInfoBean
import com.bs.demo.utils.HttpUtil
import com.bs.demo.utils.SystemUtil
import com.google.gson.Gson
import io.rong.imkit.RongIM
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * 我的tab
 */
class MineFragment : BaseFragment() {
    override fun initView() {
        tv_mine_logout.setOnClickListener {
            LocalBeanInfo.refreshUserInfo(null)
            RongIM.getInstance().logout()
            startAct(LoginActivity::class.java)
            activity?.finish()
        }
        lly_mine_reset_pwd.setOnClickListener {
            startAct(ResetPwdActivity::class.java)
        }
        lly_mine_service.setOnClickListener {
            SystemUtil.callPhone(context,"12345678")
        }
        lly_mine_account.setOnClickListener {
            startActWithId(UserInfoActivity::class.java,LocalBeanInfo.userInfo?.id.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        HttpUtil.newInstance()
            .addParam("id", LocalBeanInfo.userInfo?.id.toString())
            .post("getUserInfo", object : HttpUtil.HttpListener{
                override fun onSuccess(t: String) {
                    val bean=t.toBean(UserInfoBean::class.java)
                    LocalBeanInfo.refreshUserInfo(bean)
                    tv_mine_username.text=bean.name
                    tv_mine_id.text="Id: "+bean.id
                    iv_mine_userlogo.loadCircle(bean.logo,R.drawable.icon_def_avatar)
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
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }


    companion object {
        fun newInstance() = MineFragment().apply {}
    }
}
