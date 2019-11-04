package com.example.swell

import androidx.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen

class SwellApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}