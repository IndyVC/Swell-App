package com.example.swell.domain

class User(val firstName:String, val lastName:String, val email:String, val favourites:ArrayList<Spot>){

    lateinit var currentSpot: Spot

    fun checkIn(spot:Spot){
        this.currentSpot = spot
        spot.checkIn(this)
    }
    fun addFavourite(spot:Spot){
        this.favourites.add(spot)
    }

    fun removeFavourite(spot:Spot){
        this.favourites.remove(spot)
    }
}