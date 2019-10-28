package com.example.swell.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SpotDao {

    @Query("select * from DatabaseSpot")
    fun getAllSpots(): LiveData<List<DatabaseSpot>>

    @Query("select * from DatabaseSpot where magicSeaWeedSpotId = 194")
    fun getNazare(): LiveData<List<DatabaseSpot>>

    @Query("select * from DatabaseSpot where magicSeaWeedSpotId = 4048")
    fun getDePanne(): LiveData<List<DatabaseSpot>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg spots: DatabaseSpot)

    @Query("delete from DatabaseSpot")
    fun deleteAll()
}

@Database(entities = [DatabaseSpot::class], version = 2)
abstract class SpotDatabase : RoomDatabase() {
    abstract val spotDao: SpotDao
}

private lateinit var INSTANCE: SpotDatabase
fun getDatabase(context: Context): SpotDatabase {
    synchronized(SpotDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SpotDatabase::class.java,
                "spots"
            )
                .fallbackToDestructiveMigration()
                .build()

        }
    }
    return INSTANCE
}