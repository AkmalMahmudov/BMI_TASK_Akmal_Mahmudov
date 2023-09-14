package com.akmal.bmi_akmal_mahmudov.app

import android.app.Application
import com.akmal.bmi_akmal_mahmudov.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}