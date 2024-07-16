package com.mykodo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LotteryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}