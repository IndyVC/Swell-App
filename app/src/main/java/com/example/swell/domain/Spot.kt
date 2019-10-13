package com.example.swell.domain

class Spot(var totalCheckIn:Int, val name:String, var currentUsers:ArrayList<User>){

    fun checkIn(user:User){
        this.currentUsers.add(user)
        totalCheckIn++
    }
}