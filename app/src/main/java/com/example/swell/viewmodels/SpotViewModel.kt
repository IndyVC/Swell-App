package com.example.swell.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.swell.database.getDatabase
import com.example.swell.repository.SpotRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SpotViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = SpotRepository(database)

    val allSpots = repository.spots
    val nazare = repository.nazare
    val dePanne = repository.dePanne

    fun retrieveSpot(spotName: String) {
        viewModelScope.launch {
            when (spotName) {
                "Nazare" -> {
                    repository.retrieveSpot(194L)
                }
                "De Panne" -> {
                    repository.retrieveSpot(4048L)
                }
                else -> throw IllegalArgumentException("Spot not supported")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}