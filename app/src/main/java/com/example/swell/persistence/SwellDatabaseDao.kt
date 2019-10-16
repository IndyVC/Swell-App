package com.example.swell.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.swell.domain.Spot

@Dao
interface SwellDatabaseDao{

    @Insert
    fun insert(spot:FavouriteSpot)

    @Update
    fun update(spot:FavouriteSpot)

    @Query("SELECT * from favourite_spots ")
    fun getAllFavourites():LiveData<List<FavouriteSpot>>

    @Query("SELECT * FROM favourite_spots WHERE favouriteSpotId=:id")
    fun getFavourite(id:Long):FavouriteSpot?
}