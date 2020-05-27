package com.bs.demo.common.local

import com.bs.demo.common.TApplication
import com.bs.demo.model.UserInfoBean
import com.google.gson.Gson



/**

 * Description: 对象存储数据
 */
object LocalBeanInfo {
    private val KEY_USER_INFO = "local_userinfo"

    val userInfo: UserInfoBean?
        get() {
            return try {
                val json = TApplication.instance
                    .getPreference().getString(KEY_USER_INFO, "")
                Gson().fromJson(json, UserInfoBean::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

        }

    fun refreshUserInfo(userInfoBean: UserInfoBean?) {
        val content = Gson().toJson(userInfoBean)
        TApplication.instance.getPreference().putString(KEY_USER_INFO, content).commit()
    }




}

