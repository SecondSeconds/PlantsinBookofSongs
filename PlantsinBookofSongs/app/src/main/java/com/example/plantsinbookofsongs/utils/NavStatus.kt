package com.example.plantsinbookofsongs.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NavStatusViewModel:ViewModel(){
    // 使用MutableStateOf来持有一个可观察的状态变量

    private var _shouldShowBottomNavigation= mutableStateOf(true)
    val shouldShowBottomNavigation: State<Boolean>
        get()=_shouldShowBottomNavigation


    // 一个函数，用于在导航到Detail页面时隐藏底部导航栏
    fun hideBottomNavigation() {
        _shouldShowBottomNavigation.value = false
    }

    // 如果需要，你也可以有一个函数来重新显示底部导航栏
    fun showBottomNavigation() {
        _shouldShowBottomNavigation.value = true
    }
}