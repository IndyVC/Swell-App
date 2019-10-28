package com.example.swell.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.swell.database.SpotDatabase
import com.example.swell.database.asDomainModel
import com.example.swell.domain.Spot
import com.example.swell.network.Network
import com.example.swell.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpotRepository(private val database: SpotDatabase) {

    var spots: LiveData<List<Spot>> = Transformations.map(database.spotDao.getCurrentSpotData()) {
        it.asDomainModel()
    }

    var newestSpot: LiveData<Spot> = Transformations.map(database.spotDao.getNewestSpotData()) {
        listOf(it).asDomainModel()[0]
    }

    init {
        spots = Transformations.map(database.spotDao.getCurrentSpotData()) {
            it.asDomainModel()
        }
    }
    suspend fun retrieveSpot(id: Long) {
        withContext(Dispatchers.IO) {
            val spots = Network.spots.getSpot(id, "eu").await()
            database.spotDao.deleteAll()
            database.spotDao.insertAll(*spots.asDatabaseModel(id))
        }
    }

}