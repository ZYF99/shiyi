package com.bs.demo.utils

import android.content.Context
import com.tencent.cos.xml.CosXmlServiceConfig
import com.tencent.cos.xml.CosXmlSimpleService
import com.tencent.cos.xml.exception.CosXmlClientException
import com.tencent.cos.xml.exception.CosXmlServiceException
import com.tencent.cos.xml.listener.CosXmlResultListener
import com.tencent.cos.xml.model.CosXmlRequest
import com.tencent.cos.xml.model.CosXmlResult
import com.tencent.cos.xml.transfer.COSXMLUploadTask.COSXMLUploadTaskResult
import com.tencent.cos.xml.transfer.TransferConfig
import com.tencent.cos.xml.transfer.TransferManager
import com.tencent.qcloud.core.auth.QCloudCredentialProvider
import com.tencent.qcloud.core.auth.ShortTimeCredentialProvider
import java.io.File


class UploadFileUtil {
    companion object{
        val instance:UploadFileUtil by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED){ UploadFileUtil() }
    }
    interface UploadResultListener{
        fun onSuccess(url:String)
        fun onFail(msg:String)
    }

    fun upload(context: Context, path:String, uploadResultListener: UploadResultListener){
        val transferConfig = TransferConfig.Builder().build()
        val region = "ap-guangzhou"
        val serviceConfig = CosXmlServiceConfig.Builder()
            .setRegion(region)
            .isHttps(true) // 使用 HTTPS 请求, 默认为 HTTP 请求
            .builder()
        val secretId = "AKIDgCU1Jh8wgCVdOvGdbJZbP6XjfRxEgXx6"
        val secretKey = "4xiDUGmCMHvRijW2QYT8dsuhGjxzlNks"
        val credentialProvider: QCloudCredentialProvider = ShortTimeCredentialProvider(secretId, secretKey, 300000)
        val cosXmlService= CosXmlSimpleService(context,serviceConfig,credentialProvider)
        val transferManager = TransferManager(cosXmlService, transferConfig)

        val bucket = "bs-1258287958" //存储桶，格式：BucketName-APPID

        val cosPath = File(path).name //对象在存储桶中的位置标识符，即称对象键

        val srcPath: String = path

        val uploadId: String? = null //若存在初始化分块上传的 UploadId，则赋值对应的 uploadId 值用于续传；否则，赋值 null
        // 上传对象
        val cosxmlUploadTask = transferManager.upload(bucket, cosPath, srcPath, uploadId)

        with(cosxmlUploadTask) {
            setCosXmlResultListener(object : CosXmlResultListener {
                override fun onSuccess(request: CosXmlRequest?, result: CosXmlResult) {
                    val cOSXMLUploadTaskResult = result as COSXMLUploadTaskResult
                    uploadResultListener.onSuccess(cOSXMLUploadTaskResult.accessUrl)
                }
                override fun onFail(
                    request: CosXmlRequest?,
                    exception: CosXmlClientException?,
                    serviceException: CosXmlServiceException?
                ) {
                    uploadResultListener.onFail("上传失败!")
                }
            })


        }
    }




}