package com.example.plantsinbookofsongs.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.plantsinbookofsongs.data.PoemData

class FoundPoemViewModel: ViewModel() {
    private val poemData= PoemData.getPoem()

    private var _result= mutableStateOf("")
    val result: State<String>
        get()=_result

    fun getFoundResult(poem:String) {
        if(poemData.containsKey(poem)){
            _result.value= "这首诗属于："+poemData[poem].toString()
        }else{
            _result.value="未找到这首诗，请确认您搜索无误"
        }
    }
}