package com.example.plantsinbookofsongs.vm

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.db.UserDatabase
import com.example.plantsinbookofsongs.entity.UserData
import com.example.plantsinbookofsongs.repository.UserDataRepository
import com.example.plantsinbookofsongs.state.RegisterManager
import com.example.plantsinbookofsongs.utils.md5
import kotlinx.coroutines.launch

class RegisterViewModel (val app: Application): AndroidViewModel(app){
    //实例化Database对象
    private val db: UserDatabase by lazy {
        UserDatabase.getDatabase(app)
    }

    //初始化Repository
    private var userDataRepository: UserDataRepository
    init {
        userDataRepository= UserDataRepository(db)
    }

    var isRegister=RegisterManager

    private var _userName= mutableStateOf<String>("")
    val userName: State<String>
        get()=_userName

    private var _enrollword= mutableStateOf("")
    val enrollword: State<String>
        get()=_enrollword

    private var _confirmword= mutableStateOf("")
    val confirmword: State<String>
        get()=_confirmword

    private var _info= mutableStateOf("使用注册功能")
    val info: State<String>
        get()=_info

    fun addUser(){
        if(checkNull()){
            _info.value="输入不可为空，请重新输入！"
        }else{
            if(checkWord()){
                //密码用md5简单加密
                val md5Password= md5(enrollword.value)
                viewModelScope.launch {
                    val userData= UserData(
                        0,
                        userName.value,
                        R.drawable.defaultuser,
                        md5Password
                    )
                    val userId=userDataRepository.addUserData(userData)
                    _info.value="成功注册，用户id为: $userId ！"
                    isRegister.saveRegisterStatus(true)
                    isRegister.saveUser(userDataRepository.findUser(userId))
                }
            }else{
                _info.value="两次输入密码不同，请重新输入！"
            }
        }
    }

    private fun checkWord():Boolean{
        //两次输入密码是否相同
        //==:值 ===:地址
        return confirmword.value == enrollword.value
    }
    private fun checkNull():Boolean{
        return userName.value==null||enrollword.value==null||confirmword.value==null
    }
    fun updateUserName(name:String){
        _userName.value=name
    }
    fun updateEnrollWord(eWord:String){
        _enrollword.value=eWord
    }
    fun updateConfirmWord(cWord:String){
        _confirmword.value=cWord
    }
}