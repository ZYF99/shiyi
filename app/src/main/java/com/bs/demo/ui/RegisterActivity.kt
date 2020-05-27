package com.bs.demo.ui

import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.utils.HttpUtil
import com.bs.demo.widget.BottomListDialog
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setNavBarTitle("注册")
        tv_reg_submit.setOnClickListener {
            var account=edt_reg_tel.text.toString()
            var pwd=edt_reg_pwd.text.toString()
            if (account.isNullOrEmpty()){
                showToast(edt_reg_tel.hint.toString())
                return@setOnClickListener
            }
            if (pwd.isNullOrEmpty()){
                showToast(edt_reg_pwd.hint.toString())
                return@setOnClickListener
            }
            HttpUtil.newInstance()
                .addParam("account", account)
                .addParam("password", pwd)
                .addParam("level",0.toString())
                .post("register", object : HttpUtil.HttpListener{
                    override fun onSuccess(t: String?) {
                        showToast("注册成功!")
                        finish()
                    }

                    override fun onError(msg: String?) {
                        showToast("注册失败")
                    }
                })

        }
    }
}
