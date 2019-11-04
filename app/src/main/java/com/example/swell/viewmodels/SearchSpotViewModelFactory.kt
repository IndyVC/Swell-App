package com.example.swell.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchSpotViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchSpotViewModel::class.java)) {
            return SearchSpotViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}