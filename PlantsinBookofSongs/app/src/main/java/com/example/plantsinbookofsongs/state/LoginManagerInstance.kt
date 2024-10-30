package com.example.plantsinbookofsongs.state

import com.example.plantsinbookofsongs.entity.UserData


// 登录管理器，使用object关键字实现单例
object LoginManager {

    // 登录状态
    private var isLoggedIn = false

    private lateinit var userdata:UserData

    // 保存登录状态并发送事件
    fun saveLoginStatus(isLoggedIn: Boolean) {
        this.isLoggedIn = isLoggedIn
    }

    fun saveUser(user:UserData){
        this.userdata=user
    }

    // 获取登录状态
    fun getLoginStatus() = isLoggedIn

    //获取登录的用户数据
    fun getUser()= userdata
}