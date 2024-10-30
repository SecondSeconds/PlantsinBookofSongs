package com.example.plantsinbookofsongs.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantsinbookofsongs.dao.StarDataDao
import com.example.plantsinbookofsongs.dao.UserDataDao
import com.example.plantsinbookofsongs.entity.StarData
import com.example.plantsinbookofsongs.entity.UserData

@Database(
    entities = [UserData::class, StarData::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDataDao(): UserDataDao
    abstract fun starDataDao(): StarDataDao

    companion object{
        @Volatile
        private var db:UserDatabase?=null
        fun getDatabase(app: Application):UserDatabase{
            return db ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    app,
                    UserDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
        //多个线程有可能会遇到竞态条件并同时请求数据库实例，
        // 导致产生两个数据库而不是一个。
        // 封装代码以在 synchronized 块内获取数据库意味着一次只有一个执行线程可以进入此代码块，
        // 从而确保数据库仅初始化一次。
    }
}