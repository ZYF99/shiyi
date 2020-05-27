package com.bs.demo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.utils.RxCountDownUtils
import com.tbruyelle.rxpermissions2.RxPermissions

class SplashActivity : BaseActivity() {
    private lateinit var rxCountDownUtils: RxCountDownUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val countTime = 3
        rxCountDownUtils = RxCountDownUtils()
        rxCountDownUtils.countdown(countTime, object : RxCountDownUtils.RxCountdownFinishedListener {
            @SuppressLint("CheckResult")
            override fun onFinished() {
                RxPermissions(this@SplashActivity)
                    .requestEachCombined(Manifest.permission.CAMERA
                        , Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.READ_CONTACTS
                        ,Manifest.permission.READ_CALENDAR
                        ,Manifest.permission.WRITE_CALENDAR
                        , Manifest.permission.READ_PHONE_STATE).subscribe {
                        val userBean=LocalBeanInfo.userInfo
                        if (userBean==null){
                            startAct(LoginActivity::class.java)
                        }else{
                            startAct(MainActivity::class.java)
                        }
                        finish()

                    }
            }

            override fun onExecute(aLong: Long) {

            }
        })
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onDestroy() {
        rxCountDownUtils.cancel()
        super.onDestroy()
    }
}
