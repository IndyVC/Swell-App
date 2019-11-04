package com.example.swell.viewmodels

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel

class SessionViewModel(application: Application) : AndroidViewModel(application) {

    val locations = ArrayList<Location?>()

    private var _listening: Boolean = true
    val listening: Boolean
        get() = _listening

    fun stopListening() {
        _listening = false
    }
}