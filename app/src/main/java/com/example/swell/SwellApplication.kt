package com.example.swell

import android.app.Application
import timber.log.Timber

class SwellApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}