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

    val spots: LiveData<List<Spot>> = Transformations.map(database.spotDao.getAllSpots()) {
        it.asDomainModel(null)
    }

    val nazare: LiveData<List<Spot>> = Transformations.map(database.spotDao.getNazare()) {
        it.asDomainModel(194L)
    }

    val dePanne: LiveData<List<Spot>> = Transformations.map(database.spotDao.getDePanne()) {
        it.asDomainModel(4048L)
    }

    suspend fun retrieveSpot(id: Long) {
        withContext(Dispatchers.IO) {
            val spots = Network.spots.getNazare(id, "eu").await()
            database.spotDao.deleteAll()
            database.spotDao.insertAll(*spots.asDatabaseModel(id))
        }
    }

}