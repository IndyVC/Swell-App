package com.example.swell.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_spots")
data class FavouriteSpot(
    @PrimaryKey(autoGenerate = true)
    var favouriteSpotId:Long = 0L,

    @ColumnInfo(name="favourite_spot_name")
    val name:String = ""
)