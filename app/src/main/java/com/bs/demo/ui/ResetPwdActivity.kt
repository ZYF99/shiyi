package com.bs.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.loadCircle
import com.bs.demo.extension.toBean
import com.bs.demo.model.UserInfoBean
import com.bs.demo.utils.HttpUtil
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import kotlinx.android.synthetic.main.fragment_mine.*

class ResetPwdActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        setNavBarTitle("修改密码")
        tv_reset_pwd_submit.setOnClickListener {
            val oldpwd=edt_reset_pwd_old.text.toString()
            val new1=edt_reset_pwd_new1.text.toString()
            val new2=edt_reset_pwd_new2.text.toString()
            if (oldpwd.isNullOrEmpty()){
                showToast(edt_reset_pwd_old.hint.toString())
                return@setOnClickListener
            }
            if (new1.isNullOrEmpty()){
                showToast(edt_reset_pwd_new1.hint.toString())
                return@setOnClickListener
            }
            if (new2.isNullOrEmpty()){
                showToast(edt_reset_pwd_new2.hint.toString())
                return@setOnClickListener
            }
            if (oldpwd!=LocalBeanInfo.userInfo?.password){
                showToast("旧密码输入错误!")
                return@setOnClickListener
            }
            if (new1!=new2){
                showToast("两次输入的新密码必须一致")
                return@setOnClickListener
            }
            HttpUtil.newInstance()
                .addParam("id", LocalBeanInfo.userInfo?.id.toString())
                .addParam("newpwd",new1)
                .post("resetPwd", object : HttpUtil.HttpListener{
                    override fun onSuccess(t: String) {
                        showToast("修改成功!")
                        finish()
                    }

                    override fun onError(msg: String?) {
                        showToast("修改失败!")
                    }
                })

        }
    }
}
