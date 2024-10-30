package com.example.plantsinbookofsongs.vm

import android.app.Application
import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.db.UserDatabase
import com.example.plantsinbookofsongs.entity.StarData
import com.example.plantsinbookofsongs.helper.TAG
import com.example.plantsinbookofsongs.helper.showLog
import com.example.plantsinbookofsongs.repository.UserDataRepository
import com.example.plantsinbookofsongs.state.LoginManager
import kotlinx.coroutines.launch

class ShowViewModel (val app: Application): AndroidViewModel(app){
    //实例化Database对象
    private val db: UserDatabase by lazy {
        UserDatabase.getDatabase(app)
    }
    //初始化Repository
    private var userDataRepository: UserDataRepository
    init {
        userDataRepository= UserDataRepository(db)
    }
    private var isLogin = LoginManager

    private val resources: Resources = getApplication<Application>().resources
    private val sentences: Array<String> = resources.getStringArray(R.array.Sentences)
    private val plantName: Array<String> = resources.getStringArray(R.array.PlantName)

    private var _starList= mutableStateListOf(mutableStateOf<String>("收藏列表"))
    val starList:List<State<String>>
        get()=_starList


    fun getStarList(){
        viewModelScope.launch {
            val userData = isLogin.getUser()
            val starIdList = userDataRepository.findStarIdList(userData.UserId)
            //去除收藏列表中的重复项
            val starIdSet=starIdList.toSet()
            _starList.clear()
            if(starIdSet.isEmpty()){
                _starList.add(mutableStateOf("收藏列表为空"))
            }else{
                _starList.add(mutableStateOf("收藏列表如下"))
            }
            for(id in starIdSet){
                _starList.add(mutableStateOf(plantName[id.toInt()]+"\n"+sentences[id.toInt()]))
            }
        }
    }
}