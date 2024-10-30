package com.example.plantsinbookofsongs

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.plantsinbookofsongs.dao.StarDataDao
import com.example.plantsinbookofsongs.dao.UserDataDao
import com.example.plantsinbookofsongs.db.UserDatabase
import com.example.plantsinbookofsongs.entity.StarData
import com.example.plantsinbookofsongs.helper.DbHelper
import com.example.plantsinbookofsongs.helper.showLog
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws


@RunWith(AndroidJUnit4::class)
class UserDataBaseTest {

    private lateinit var userDataDao: UserDataDao
    private lateinit var starDataDao: StarDataDao
    private lateinit var db: UserDatabase

    @Before
    fun createDb(){

        val context= InstrumentationRegistry
            .getInstrumentation().targetContext
        db= Room.inMemoryDatabaseBuilder(
            context,UserDatabase::class.java
        ).build()
        userDataDao=db.userDataDao()
        starDataDao=db.starDataDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    fun addUserTest()= runBlocking{
        val userData= DbHelper.createExampleUserData()
        val number=userDataDao.addUser(userData)
        showLog("$userData 已插入")
        val objFromDb=userDataDao.findUserById(number)
        showLog("从数据库中提取的UserId记录：$objFromDb")
        Assert.assertNotNull(objFromDb)
    }

    @Test
    fun addStarTest()= runBlocking{
        val userData=DbHelper.createExampleUserData()
        val userNumber=userDataDao.addUser(userData)
        showLog("$userData 已插入")
        //由于外键，必须user表中存在userId，star表中才能插入有该userId的stardata
        val starData= StarData(0,userNumber,0)
        val starNumber=starDataDao.addStar(starData)
        showLog("$starData 已插入")
        val objFromDb = starDataDao.findStarDataById(starNumber)
        showLog("从数据库中提取的StarId记录：$objFromDb")
        Assert.assertNotNull(objFromDb)
    }

}