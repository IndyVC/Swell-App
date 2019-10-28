package com.example.swell.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CurrentSpotViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentSpotViewModel::class.java)) {
            return CurrentSpotViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}