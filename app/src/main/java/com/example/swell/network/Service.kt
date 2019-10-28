package com.example.swell.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface SpotService {
    @GET(".")
    fun getSpot(@Query("spot_id") spotId: Long, @Query("unit") unit: String): Deferred<List<NetworkSpot>>
}

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Main entry point for network access. Call like `Network.devbytes.getPlaylist()`
 */
object Network {

    private val BASE_URL = "http://magicseaweed.com/api/a2ba235ecf09624fe6ad2a6a2c9e7449/forecast/"
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val spots = retrofit.create(SpotService::class.java)


    fun getDate(timestamp: Long?): Date? {
        try {
            val date = Date(timestamp!! * 1000L)
            return date
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }
}