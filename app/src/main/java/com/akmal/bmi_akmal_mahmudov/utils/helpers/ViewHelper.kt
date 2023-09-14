package com.akmal.bmi_akmal_mahmudov.utils.helpers

import android.app.Activity
import android.content.ContextWrapper
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.*
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

val MY_DP = Resources.getSystem().displayMetrics.density.toInt()

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.getActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun View.setCardBackground(
    gradients: IntArray,
    orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TL_BR,
    topLeftRadius: Float,
    topRightRadius: Float,
    btmRightRadius: Float,
    btmLeftRadius: Float,
) {

    val gd = GradientDrawable(orientation, gradients)
    gd.cornerRadii = floatArrayOf(
        MY_DP * topLeftRadius,
        MY_DP * topLeftRadius,
        MY_DP * topRightRadius,
        MY_DP * topRightRadius,
        MY_DP * btmRightRadius,
        MY_DP * btmRightRadius,
        MY_DP * btmLeftRadius,
        MY_DP * btmLeftRadius
    )
    this.background = gd
}

fun View.setCardBackground(
    gradients: IntArray,
    orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TL_BR,
    topLeftRadius: Float,
    topRightRadius: Float,
    btmRightRadius: Float,
    btmLeftRadius: Float,
    borderColor: Int,
) {

    val gd = GradientDrawable(orientation, gradients)
    gd.setStroke(1, borderColor)
    gd.cornerRadii = floatArrayOf(
        MY_DP * topLeftRadius,
        MY_DP * topLeftRadius,
        MY_DP * topRightRadius,
        MY_DP * topRightRadius,
        MY_DP * btmRightRadius,
        MY_DP * btmRightRadius,
        MY_DP * btmLeftRadius,
        MY_DP * btmLeftRadius
    )
    this.background = gd
}

fun View.setSolidBackground(
    color: Int,
    topLeftRadius: Float,
    topRightRadius: Float,
    btmRightRadius: Float,
    btmLeftRadius: Float,
) {

    val gd = ShapeDrawable(
        RoundRectShape(
            floatArrayOf(
                MY_DP * topLeftRadius,
                MY_DP * topLeftRadius,
                MY_DP * topRightRadius,
                MY_DP * topRightRadius,
                MY_DP * btmRightRadius,
                MY_DP * btmRightRadius,
                MY_DP * btmLeftRadius,
                MY_DP * btmLeftRadius
            ), null, null
        )
    )
    gd.paint.color = color
    this.background = gd
}

fun View.setStrokeBackground(
    color: Int,
    topLeftRadius: Float,
    topRightRadius: Float,
    btmRightRadius: Float,
    btmLeftRadius: Float,
    borderWidth: Float,
) {

    val gd = ShapeDrawable(
        RoundRectShape(
            floatArrayOf(
                MY_DP * topLeftRadius,
                MY_DP * topLeftRadius,
                MY_DP * topRightRadius,
                MY_DP * topRightRadius,
                MY_DP * btmRightRadius,
                MY_DP * btmRightRadius,
                MY_DP * btmLeftRadius,
                MY_DP * btmLeftRadius
            ), null, null
        )
    )

    gd.paint.style = Paint.Style.STROKE
    gd.paint.strokeWidth = borderWidth.dp
    gd.paint.color = Color.GREEN

    this.background = gd
}


fun View.setSelectorDrawable(
    colorActive: Int,
    colorDisable: Int,
    topLeftRadius: Float,
    topRightRadius: Float,
    btmRightRadius: Float,
    btmLeftRadius: Float,
) {
    val gdActive = ShapeDrawable(
        RoundRectShape(
            floatArrayOf(
                MY_DP * topLeftRadius,
                MY_DP * topLeftRadius,
                MY_DP * topRightRadius,
                MY_DP * topRightRadius,
                MY_DP * btmRightRadius,
                MY_DP * btmRightRadius,
                MY_DP * btmLeftRadius,
                MY_DP * btmLeftRadius
            ), null, null
        )
    )
    val res = StateListDrawable()
    gdActive.paint.color = colorActive


    val gdDisable = ShapeDrawable(
        RoundRectShape(
            floatArrayOf(
                MY_DP * topLeftRadius,
                MY_DP * topLeftRadius,
                MY_DP * topRightRadius,
                MY_DP * topRightRadius,
                MY_DP * btmRightRadius,
                MY_DP * btmRightRadius,
                MY_DP * btmLeftRadius,
                MY_DP * btmLeftRadius
            ), null, null
        )
    )
    gdDisable.paint.color = colorDisable

    res.addState(intArrayOf(android.R.attr.state_enabled), gdActive)

    res.addState(intArrayOf(-android.R.attr.state_enabled), gdDisable)
    this.background = res

}

fun TextView.setTextViewDrawableColor(color: Int) {
    for (drawable in this.compoundDrawables) {
        if (drawable != null) {
            drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }
}

fun View.setSquareBackground(gradients: IntArray) {

    val gd = GradientDrawable(GradientDrawable.Orientation.TL_BR, gradients)
//    gd.cornerRadii = floatArrayOf(40F,40F,40F,40F,40F,40F,40F,40F)
    this.background = gd
}

fun View.setCircleBackground(gradients: IntArray) {

    val gd = GradientDrawable(GradientDrawable.Orientation.TL_BR, gradients)
//    gd.cornerRadii = floatArrayOf(40F,40F,40F,40F,40F,40F,40F,40F)
    gd.shape = GradientDrawable.OVAL
    this.background = gd

}


fun Activity.transparentStatusBar() {
    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true, window)
    if (VERSION.SDK_INT >= VERSION_CODES.M) {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    if (VERSION.SDK_INT >= 21) {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false, window)
        window.statusBarColor = Color.TRANSPARENT
    }
}

fun Activity.makeStatusBarTransparent() {
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}


fun Activity.resetStatusBar() {
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = 0
            }
            statusBarColor = Color.WHITE
        }
    }
}

fun Activity.clearTransparentStatusBar() {
    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.WHITE
        }
    }
}

private fun setWindowFlag(bits: Int, on: Boolean, window: Window) {
    val winParams = window.attributes
    if (on) {
        winParams.flags = winParams.flags or bits
    } else {
        winParams.flags = winParams.flags and bits.inv()
    }
    window.attributes = winParams
}

val Float.dp: Float get() = (this * Resources.getSystem().displayMetrics.density + 0.5f)