package com.akmal.bmi_akmal_mahmudov.utils.extensions

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat


val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun setLightTheme() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}

fun setDarkTheme() {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
}

fun View.longSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.shortSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.snackBarWithAction(message: String, actionLabel: String, onClicked: () -> Unit) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).setAction(actionLabel) { onClicked() }.show()
}

fun Float.split(): Pair<String, String> {
    val wholePart = this.toInt().toString()
    val fractionalPart = (this - wholePart.toDouble()).toString().substring(1)
    return Pair(wholePart, fractionalPart)
}

fun Float.roundToDecimalPlaces(): String {
    val pattern = buildString {
        append("#.")
        repeat(2) { append("#") }
    }
    val df = DecimalFormat(pattern)
    return df.format(this).toFloat().toString()
}