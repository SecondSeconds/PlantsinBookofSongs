package com.example.plantsinbookofsongs.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

//收藏表
@Entity(tableName = "starData",
    foreignKeys = [ForeignKey(
        entity = UserData::class,
        parentColumns = ["UserId"],
        childColumns = ["UserId"],
        onDelete = ForeignKey.CASCADE
    )]
    , indices = [Index(value = ["UserId"])]
)
data class StarData (
    @PrimaryKey(autoGenerate =true)
    val number:Long,
    @ColumnInfo(name = "UserId")
    val UserId:Long,
    //userId为外键
    var StarId:Long
)
