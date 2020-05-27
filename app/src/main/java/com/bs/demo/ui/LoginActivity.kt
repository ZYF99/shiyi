package com.bs.demo.ui

import android.net.Uri
import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.toBean
import com.bs.demo.model.UserInfoBean
import com.bs.demo.utils.HttpUtil
import io.rong.imkit.RongIM
import io.rong.imkit.RongIM.UserInfoProvider
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.UserInfo
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setNavBarTitle("登陆")
        navigationBar?.hideBack()
        navigationBar?.setRightStyleBtnText("注册")
        navigationBar?.setNavBarRightBtnListener {
            startAct(RegisterActivity::class.java)
        }
        RongIM.getInstance().logout()
        tv_login_submit.setOnClickListener {
            var account=edt_login_tel.text.toString()
            var pwd=edt_login_pwd.text.toString()
            if (account.isNullOrEmpty()){
                showToast(edt_login_tel.hint.toString())
                return@setOnClickListener
            }
            if (pwd.isNullOrEmpty()){
                showToast(edt_login_pwd.hint.toString())
                return@setOnClickListener
            }
            //startAct(MainActivity::class.java)

            HttpUtil.newInstance()
                .addParam("account", account)
                .addParam("password", pwd)
                .post("login", object : HttpUtil.HttpListener {
                    override fun onSuccess(t: String) {
                        val bean=t.toBean(UserInfoBean::class.java)
                        LocalBeanInfo.refreshUserInfo(bean)
                        RongIM.connect(bean.token, object : RongIMClient.ConnectCallback() {
                            override fun onTokenIncorrect() {}
                            override fun onSuccess(userid: String) {
                                LocalBeanInfo.userInfo?.let {
                                    RongIM.getInstance().setCurrentUserInfo(UserInfo(
                                        it.id.toString(),
                                        it.name,
                                        Uri.parse(it.logo)
                                    ))
                                }
                                showToast("登陆成功!")
                                startAct(MainActivity::class.java)
                                finish()
                            }

                            override fun onError(errorCode: RongIMClient.ErrorCode) {
                                showToast("登陆失败!")
                            }
                        })


                    }

                    override fun onError(msg: String?) {
                        showToast("登陆失败!")
                    }
                })
        }

    }
}
