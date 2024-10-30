package com.example.plantsinbookofsongs.state

import com.example.plantsinbookofsongs.entity.UserData


// 注册管理器，使用object关键字实现单例
object RegisterManager {

    // 注册状态
    private var isRegistered = false

    private lateinit var userdata: UserData

    // 保存注册状态
    fun saveRegisterStatus(isRegistered: Boolean) {
        this.isRegistered = isRegistered
    }

    fun saveUser(user: UserData){
        this.userdata=user
    }

    // 获取注册状态
    fun getRegisterStatus() = isRegistered

    fun getUser()= userdata
}