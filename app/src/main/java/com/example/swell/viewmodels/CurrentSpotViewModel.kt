package com.example.swell.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.swell.database.getDatabase
import com.example.swell.domain.Spot
import com.example.swell.repository.SpotRepository
import com.example.swell.util.ConvertSpot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class CurrentSpotViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = SpotRepository(database)

    var spots: LiveData<List<Spot>>? = repository.spots

    private var _currentSpot = MutableLiveData<Spot>()
    val currentSpot: LiveData<Spot>
        get() = _currentSpot

    init {
        Timber.i("TEST")
        _currentSpot.value = spots?.value?.last()
    }

    fun retrieveSpot(spot: String?, refresh: Boolean) {

        viewModelScope.launch {
            val id = ConvertSpot.nameToId(spot)
            if (refresh) {
                repository.retrieveSpot(id)
            }
            spots = repository.spots
        }
    }

    fun setCurrentSpot(spotId: Long?) {
        if (spotId == null && spots?.value!!.count() > 0) {
            _currentSpot.value = spots?.value?.last()
        }
        if (spots?.value != null) {
            for (spot in spots?.value!!) {
                if (spot.spotId == spotId) {
                    _currentSpot.value = spot
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}