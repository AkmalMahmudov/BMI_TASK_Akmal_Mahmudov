package com.akmal.bmi_akmal_mahmudov.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.loadImage(data: String?, errorDrawable: Drawable? = null) {
    if (data == null) return

    Glide.with(context)
        .load(data)
        .diskCacheStrategy(DiskCacheStrategy.ALL) // for caching purposes
        .error(errorDrawable)
//        .centerInside()
        .into(this)
}

fun ImageView.loadImage(data: String?, corners: Int? = null) {
    if (data == null) return

    Glide.with(context)
        .load(data)
        .diskCacheStrategy(DiskCacheStrategy.ALL) // for caching purposes
        .transform(RoundedCorners((corners ?: 1).dp))
//        .centerInside()
        .into(this)
}

fun ImageView.loadImage(@DrawableRes data: Int?) {
    if (data == null) return
    Glide.with(this)
        .load(data)
        .into(this)
}