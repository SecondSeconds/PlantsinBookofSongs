package com.example.plantsinbookofsongs.vm

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantsinbookofsongs.db.UserDatabase
import com.example.plantsinbookofsongs.repository.UserDataRepository
import com.example.plantsinbookofsongs.state.LoginManager
import com.example.plantsinbookofsongs.utils.md5
import kotlinx.coroutines.launch

class LoginViewModel (val app: Application): AndroidViewModel(app){
    //实例化Database对象
    private val db: UserDatabase by lazy {
        UserDatabase.getDatabase(app)
    }
    //初始化Repository
    private var userDataRepository: UserDataRepository
    init {
        userDataRepository=UserDataRepository(db)
    }

    //记录App是否登录
    private var isLogin=LoginManager

    private var _userId= mutableStateOf<Long>(1)
    val userId: State<Long>
        get()=_userId


    private var _password= mutableStateOf("")
    val password: State<String>
        get()=_password

    private var _info= mutableStateOf("使用登录功能")
    val info: State<String>
        get()=_info

    fun findUser(){
        viewModelScope.launch {
            if(checkNull()){
                _info.value="输入为空，请重新输入！"
            }else{
                val foundUser=userDataRepository.findUser(userId.value)
                if(foundUser==null){
                    _info.value ="未注册，请先进行注册！"
                }else{
                    //比对加密后的两个密码
                    val md5Password:String= md5(password.value)
                    if(foundUser.password == md5Password){
                        _info.value="成功登录！"
                        //登录后显示内容
                        //
                        isLogin.saveLoginStatus(true)
                        isLogin.saveUser(foundUser)
                    }else{
                        _info.value="账号密码错误，请重新输入！"
                    }
                }
            }

        }
    }

    private fun checkNull():Boolean{
        return userId.value==null||password.value==null
    }
    fun updateUserId(id:Long){
        _userId.value=id
    }
    fun updateUserPassword(word:String){
        _password.value=word
    }
}