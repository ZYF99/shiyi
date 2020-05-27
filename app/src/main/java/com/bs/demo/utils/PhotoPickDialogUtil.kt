package com.bs.demo.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.bs.demo.R
import com.bs.demo.widget.PickPhotoDialog
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.tbruyelle.rxpermissions2.RxPermissions

import io.reactivex.functions.Consumer

/**
 * Description: 图片选择对话框
 */
class PhotoPickDialogUtil {
    private var onPickListener: OnPickListener? = null
    private var selectCount = 1
    fun setOnPickListener(onPickListener: OnPickListener?): PhotoPickDialogUtil {
        this.onPickListener = onPickListener
        return this
    }

    interface OnPickListener {
        fun onSelected(paths: List<String>)
    }

    fun setSelectCount(selectCount: Int): PhotoPickDialogUtil {
        this.selectCount = selectCount
        return this
    }

    fun show(context: FragmentActivity) {
        if (selectCount <= 0) {
            return
        }
        RxPermissions(context)
            .requestEachCombined(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
              //  Manifest.permission.CAMERA
            )
            .subscribe { aBoolean ->
                if (aBoolean.granted) {
                    PickPhotoDialog.newInstance()
                        .setOnItemClickListener(object : PickPhotoDialog.OnDialogItemClickListener {
                            override fun fromAlbum() {
                                PictureSelector.create(context)
                                    .openGallery(PictureMimeType.ofImage())
                                    .maxSelectNum(selectCount)
                                    .enableCrop(false)
                                    .isCamera(false)
                                    .isAndroidQTransform(true)
                                    .isWeChatStyle(true)
                                    .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                                    .forResult {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                                            onPickListener?.onSelected(it.map { it.androidQToPath })

                                        }else{
                                            onPickListener?.onSelected(it.map { it.path })
                                        }
                                    }
                            }

                            override fun fromTakePhoto() {
                                PictureSelector.create(context)
                                    .openCamera(PictureMimeType.ofImage())
                                    .maxSelectNum(1)
                                    .enableCrop(false)
                                    .isWeChatStyle(true)
                                    .isAndroidQTransform(true)
                                    .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                                    .forResult {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                                            onPickListener?.onSelected(it.map { it.androidQToPath })

                                        }else{
                                            onPickListener?.onSelected(it.map { it.path })
                                        }

                                    }

                            }
                        }).show(context.supportFragmentManager)
                } else {
                    Toast.makeText(context,"请到设置中打开该权限",Toast.LENGTH_SHORT).show()

                }
            }
    }

    companion object {
        private var photoPickDialogUtil: PhotoPickDialogUtil? = null
        fun newInstance(): PhotoPickDialogUtil {
            if (photoPickDialogUtil == null) {
                photoPickDialogUtil = PhotoPickDialogUtil()
            }
            return photoPickDialogUtil!!
        }
    }
}