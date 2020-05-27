package com.bs.demo.common.config


import android.os.Environment
import com.bs.demo.common.TApplication

import java.io.File

/**
 * Description: 全局常量
 */
object Constants {

    //默认文件保存地址
    fun getDownloadDefDir(): String {
        val externalSaveDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        return if (externalSaveDir == null) {
            TApplication.instance.cacheDir.absolutePath + File.separator
        } else {
            externalSaveDir.absolutePath + File.separator
        }
    }
}
