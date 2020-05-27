package com.bs.demo.extension

import com.bs.demo.utils.GsonUtil
import com.google.gson.Gson
import java.lang.reflect.Type

fun <T>String.toBean(clazz: Class<T>):T{
    return Gson().fromJson<T>(this, clazz)
}
fun <T>String.toBeans(clazz: Class<T>):List<T>{
    return GsonUtil.parseStr2List(this,clazz)
}