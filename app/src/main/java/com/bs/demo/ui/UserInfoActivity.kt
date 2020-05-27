package com.bs.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.config.ActKeyConstants
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.load
import com.bs.demo.extension.loadCircle
import com.bs.demo.extension.toBean
import com.bs.demo.model.UserInfoBean
import com.bs.demo.utils.HttpUtil
import com.bs.demo.utils.LogUtil
import com.bs.demo.utils.PhotoPickDialogUtil
import com.bs.demo.utils.UploadFileUtil
import com.bs.demo.widget.BottomListDialog
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.fragment_home.*

class UserInfoActivity : BaseActivity() {
    var id=""
    lateinit var bean:UserInfoBean
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        id=intent.getStringExtra(ActKeyConstants.KEY_ID)
        setNavBarTitle("个人信息")
        navigationBar?.setRightStyleBtnText("保存")
        navigationBar?.setNavBarRightBtnListener {
            HttpUtil.newInstance()
                .addParam("id", id)
                .addParam("name",edt_account_info_name.text.toString())
                .addParam("phone",edt_account_info_phone.text.toString())
                .addParam("job",edt_account_info_job.text.toString())
                .addParam("logo",bean.logo?:"")
                .addParam("age",edt_account_info_age.text.toString())
                .addParam("gender",tv_account_info_gender.text.toString())
                .post("resetUserInfo", object : HttpUtil.HttpListener{
                    override fun onSuccess(t: String) {
                        showToast("保存成功!")
                    }

                    override fun onError(msg: String?) {
                        showToast("保存失败!")
                    }
                })
        }
        rly_userinfo_logo.setOnClickListener {
            PhotoPickDialogUtil.newInstance().setSelectCount(1)
                .setOnPickListener(object : PhotoPickDialogUtil.OnPickListener {
                    override fun onSelected(paths: List<String>) {
                        showLoadingDialog()
                        UploadFileUtil.instance
                            .upload(this@UserInfoActivity,paths.first(),object :
                                UploadFileUtil.UploadResultListener {
                                override fun onSuccess(url: String) {
                                    runOnUiThread {
                                        dismissLoadingDialog()
                                        this@UserInfoActivity.iv_account_info_logo.loadCircle(url)
                                        this@UserInfoActivity.bean.logo=url
                                    }
                                }

                                override fun onFail(msg: String) {
                                    showToast(msg)
                                }
                            })
                    }
                }).show(this)
        }
        tv_account_info_gender.setOnClickListener {
            BottomListDialog.newInstance()
                .apply {
                    this.title="选项性别"
                }
                .addData("男")
                .addData("女")
                .setCallBack(object : BottomListDialog.CallBack {
                    override fun onItemClick(postion: Int, title: String) {
                        tv_account_info_gender.text=title
                    }
                }).show(supportFragmentManager)
        }
        HttpUtil.newInstance()
            .addParam("id", id)
            .post("getUserInfo", object : HttpUtil.HttpListener{
                override fun onSuccess(t: String) {
                    bean=t.toBean(UserInfoBean::class.java)
                    iv_account_info_logo.loadCircle(bean.logo,R.drawable.icon_def_avatar)
                    edt_account_info_age.setText(bean.age.toString())
                    edt_account_info_job.setText(bean.job)
                    edt_account_info_name.setText(bean.name)
                    edt_account_info_phone.setText(bean.phone)
                    tv_account_info_gender.text=bean.gender

                }

                override fun onError(msg: String?) {
                    showToast(msg)
                    finish()
                }
            })
    }
}
