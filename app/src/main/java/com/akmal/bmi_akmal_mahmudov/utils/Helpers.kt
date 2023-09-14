package com.akmal.bmi_akmal_mahmudov.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceHelper @Inject constructor(@ApplicationContext private val context: Context) {
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    fun getString(@StringRes stringResId: Int, args: String): String {
        return context.getString(stringResId, args)
    }
}