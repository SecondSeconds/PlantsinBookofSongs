package com.example.plantsinbookofsongs.vm

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantsinbookofsongs.db.UserDatabase
import com.example.plantsinbookofsongs.entity.StarData
import com.example.plantsinbookofsongs.repository.UserDataRepository
import com.example.plantsinbookofsongs.state.LoginManager
import kotlinx.coroutines.launch

class StarViewModel (val app: Application): AndroidViewModel(app){
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

    private var _info= mutableStateOf("使用收藏功能")
    val info: State<String>
        get()=_info

    fun addStar(number:Int){
        viewModelScope.launch {
            if(isLogin.getLoginStatus()){
                val userData=isLogin.getUser()
                val starData=StarData(
                    0,userData.UserId,number.toLong()
                )
                userDataRepository.addStar(starData)
                _info.value="您已成功收藏"
            }else{
                _info.value="尚未登录，请先登录！"
            }
        }
    }
}