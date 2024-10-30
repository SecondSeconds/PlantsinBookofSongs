package com.example.plantsinbookofsongs.helper

import android.util.Log
import com.example.plantsinbookofsongs.entity.UserData
import kotlin.random.Random

object DbHelper {
    fun createExampleUserData(): UserData {
        val ranValue = Random.nextInt(1,1000)
        val passWord= Random.nextInt(1500,3000)
        return UserData(0,"UserData$ranValue",0,"tyn$passWord")
    }
}
const val TAG="Room"

fun showLog(info:Any){
    Log.d(TAG,info.toString())
}