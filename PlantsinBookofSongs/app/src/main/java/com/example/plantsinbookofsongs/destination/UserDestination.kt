package com.example.plantsinbookofsongs.destination

sealed class UserDestination(val route:String){
    data object User:UserDestination("User")
    data object Visitor:UserDestination("Visitor")
    data object Login:UserDestination("login")
    data object Register:UserDestination("register")
    data object Set:UserDestination("set")
    data object Star:UserDestination("star")
}
