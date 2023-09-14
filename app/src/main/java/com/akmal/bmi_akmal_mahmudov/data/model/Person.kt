package com.akmal.bmi_akmal_mahmudov.data.model

import android.os.Parcelable
import com.google.ads.AdRequest.Gender
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Float,
    val gender: Gender,
) : Parcelable
