package com.akmal.bmi_akmal_mahmudov.utils.helpers

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

object StringHelper {
    const val APP_LANG = "uz"

    fun getTrueString(array: Array<String>): String {
        return when (APP_LANG) {
            AppLang.ru.name -> array[0]
            AppLang.uz.name -> array[1]
            else -> array[2]
        }
    }
}

class ResourceHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    fun getString(@StringRes stringResId: Int, args: String): String {
        return context.getString(stringResId, args)
    }
}

enum class AppLang {
    ru, uz, oz
}