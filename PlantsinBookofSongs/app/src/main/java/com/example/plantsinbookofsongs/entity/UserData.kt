package com.example.plantsinbookofsongs.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//用户表
@Entity(tableName = "userData")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserId")
    val UserId:Long,
    var UserName:String,
    var UserImage:Int,
    var password:String
)