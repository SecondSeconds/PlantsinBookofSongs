package com.example.plantsinbookofsongs.repository

import com.example.plantsinbookofsongs.db.UserDatabase
import com.example.plantsinbookofsongs.entity.StarData
import com.example.plantsinbookofsongs.entity.UserData

class UserDataRepository(private val db: UserDatabase) {
    suspend fun addUserData(userData: UserData):Long{
        return db.userDataDao().addUser(userData)
    }

    suspend fun findUser(id:Long):UserData{
        return db.userDataDao().findUserById(id)
    }

    suspend fun addStar(starData: StarData):Long{
        return db.starDataDao().addStar(starData)
    }

    suspend fun cancelStar(userId:Long,starId:Long){
        db.starDataDao().cancelStar(userId,starId)
    }

    suspend fun findStarList(id:Long):List<StarData>{
        return db.starDataDao().findStarDataById(id)
    }

    suspend fun findStarIdList(id:Long):List<Long>{
        val starDataList = db.starDataDao().findStarDataById(id)
        var starIdList = mutableListOf<Long>()
        for(star in starDataList){
            starIdList.add(star.StarId)
        }
        return starIdList
    }
    //测试用功能
    suspend fun clearStar(){
        return db.starDataDao().clear()
    }

    suspend fun clearUser(){
        return db.userDataDao().clear()
    }

    suspend fun getAllStar():List<StarData>{
        return db.starDataDao().getAll()
    }

    suspend fun getAllUser():List<UserData>{
        return db.userDataDao().getAll()
    }
}
