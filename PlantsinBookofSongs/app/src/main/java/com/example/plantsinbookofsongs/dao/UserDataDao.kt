package com.example.plantsinbookofsongs.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.plantsinbookofsongs.entity.UserData

@Dao
interface UserDataDao {
    //用户功能

    @Insert
    suspend fun addUser(userData: UserData):Long

    @Delete
    suspend fun deleteUser(userData: UserData)

    @Query("select * from userdata where UserId=:id")
    suspend fun findUserById(id: Long): UserData

    //测试用功能

    @Query("delete from userData")
    suspend fun clear()

    @Query("select * from userData")
    suspend fun getAll():List<UserData>
}