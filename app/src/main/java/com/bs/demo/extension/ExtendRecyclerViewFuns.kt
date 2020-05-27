package com.bs.demo.extension

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.bs.demo.utils.DensityUtil
import com.fondesa.recyclerviewdivider.RecyclerViewDivider

/**
 * 判断列表是否处于滚动状态
 */
fun RecyclerView.isSafeNotifyData():Boolean{
    this.adapter?.notifyDataSetChanged()
    return this.scrollState == RecyclerView.SCROLL_STATE_IDLE && !this.isComputingLayout
}

/**
 * 在不滚动状态下更新数据
 */
fun RecyclerView.runSafeNotifyData(){
    if (this.isSafeNotifyData()){
        this.adapter?.notifyDataSetChanged()
    }
}

fun RecyclerView.addDivider(color:Int, isShowLastDivider:Boolean){
    addDivider(color,0,isShowLastDivider)
}

fun RecyclerView.addDivider(@ColorRes color:Int,margin:Int, isShowLastDivider:Boolean){
    RecyclerViewDivider.with(context)
        .color(resources.getColor(color))
        .inset(margin,margin)
        .size(DensityUtil.dp2px(context,1f))
        .apply {
            if (!isShowLastDivider) {
                this.hideLastDivider()
            }
        }
        .build()
        .addTo(this)
}
fun RecyclerView.addSpaceDivider(sizeDp:Float, isShowLastDivider:Boolean){
    RecyclerViewDivider.with(context)
        .asSpace()
        .size(DensityUtil.dp2px(context,sizeDp))
        .apply {
            if (!isShowLastDivider) {
                this.hideLastDivider()
            }
        }
        .build()
        .addTo(this)
}
fun RecyclerView.addDivider(res:Drawable, isShowLastDivider:Boolean){
    RecyclerViewDivider.with(context)
        .drawable(res)
        .apply {
            if (!isShowLastDivider) {
                this.hideLastDivider()
            }
        }
        .build()
        .addTo(this)
}
