package com.bs.demo.extension

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bs.demo.R
import com.bs.demo.utils.DensityUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions



fun ImageView.load(url: String?) {
    url?.let {
        Glide.with(this.context).load(url)
            .placeholder(R.color.default_color)
            .error(R.color.default_color)
            .centerCrop()
            .into(this)
    }
}
fun ImageView.loadSize(url: String?, size:Int) {
    url?.let {
        Glide.with(this.context).load(url)
            .placeholder(R.color.default_color)
            .error(R.color.default_color)
            .override(DensityUtil.dp2px(context,size.toFloat()))
            .centerCrop()
            .into(this)
    }
}

fun ImageView.loadSize(url: String?, size:Int, r :Int) {
    url?.let {
        Glide.with(this.context).load(url)
            .placeholder(R.color.default_color)
            .error(R.color.default_color)
            .override(DensityUtil.dp2px(context,size.toFloat()))
            .apply(
                RequestOptions().transform(
                    CenterCrop(),
                    RoundedCorners(DensityUtil.dp2px(context,r.toFloat()))
                )
            )
            .into(this)
    }
}

fun ImageView.loadCircle(url: String?) {
    url?.let {
        Glide.with(this.context).load(url)
            .apply(
                RequestOptions()
                    .centerCrop()
                    .circleCrop()
            )

            .into(this)
    }
}
fun ImageView.loadCircle(url: String?,@DrawableRes def:Int) {
    url?.let {
        Glide.with(this.context).load(url)
            .apply(
                RequestOptions()
                    .centerCrop()
                    .circleCrop()
            )
            .error(def)
            .placeholder(def)
            .into(this)
    }
}

fun ImageView.loadCircle(bmp: Bitmap) {
    Glide.with(this.context).load(bmp)
        .apply(
            RequestOptions()
                .centerCrop()
                .circleCrop()

        )
        .into(this)

}
fun ImageView.load(bmp: Bitmap, r: Int) {
    Glide.with(this.context).load(bmp)
        .apply(
            RequestOptions().transform(
                CenterCrop(),
                RoundedCorners(DensityUtil.dp2px(context,r.toFloat()))
            )

        )
        .into(this)

}

fun ImageView.load(url: String?, r: Int) {
    url?.let {
        Glide.with(this.context).load(url)
            .placeholder(R.color.default_color)
            .error(R.color.default_color).apply(
                RequestOptions().transform(
                    CenterCrop(),
                    RoundedCorners(DensityUtil.dp2px(context,r.toFloat()))
                )
            ).into(this)
    }
}
fun ImageView.load(url: String?, r: Int,@DrawableRes error:Int) {
    url?.let {
        Glide.with(this.context).load(url)
            .placeholder(error)
            .error(error).apply(
                RequestOptions().transform(
                    CenterCrop(),
                    RoundedCorners(DensityUtil.dp2px(context,r.toFloat()))
                )
            ).into(this)
    }
}
fun ImageView.loadWithError(url: String?,@DrawableRes error:Int) {
    url?.let {
        Glide.with(this.context).load(url)
            .placeholder(error)
            .error(error).apply(
                RequestOptions().transform(
                    CenterCrop()
                )
            ).into(this)
    }
}

fun ImageView.load(@DrawableRes res: Int, r: Int) {
    Glide.with(this.context).load(res)
        .apply(
            RequestOptions().transform(
                CenterCrop(),
                RoundedCorners(DensityUtil.dp2px(context,r.toFloat()))
            )
        ).into(this)
}

fun ImageView.load(@DrawableRes res: Int) {
    Glide.with(this.context).load(res).into(this)
}





