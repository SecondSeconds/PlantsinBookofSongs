package com.example.plantsinbookofsongs.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.plantsinbookofsongs.entity.StarData


@Dao
interface StarDataDao {
    //收藏功能

    @Insert
    suspend fun addStar(starData: StarData):Long

    @Delete
    suspend fun deleteStar(starData: StarData)

    @Query("delete from starData where UserId=:userId and StarId=:starId")
    suspend fun cancelStar(userId: Long,starId:Long)

    @Query("select * from starData where UserId=:id")
    suspend fun findStarDataById(id: Long): List<StarData>

    /* 测试用功能 */

    @Query("delete from starData")
    suspend fun clear()

    @Query("select * from starData")
    suspend fun getAll():List<StarData>
}

