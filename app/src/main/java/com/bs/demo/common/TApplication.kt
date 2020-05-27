package com.bs.demo.common


import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.multidex.MultiDex

import com.bs.demo.common.base.BasePreference
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.utils.LogUtil
import io.rong.imkit.RongIM
import io.rong.imlib.model.UserInfo


class TApplication : Application(){

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)//突破64k限制
    }


    override fun onCreate() {
        super.onCreate()
        LogUtil.isDebug = true
        mApplication = this
        SharedPreferencesUtils.getInstance(this, "local")
        preference = BasePreference(this)
        RongIM.init(this)
    }

    fun getPreference(): BasePreference {
        if (preference == null) {
            preference = BasePreference(this)
        }
        return preference as BasePreference
    }

    companion object {
        private var mApplication: TApplication? = null
        private var preference: BasePreference? = null
        val instance: TApplication
            get() {
                if (mApplication == null) {
                    mApplication = TApplication()
                }
                return mApplication as TApplication
            }

    }


}
