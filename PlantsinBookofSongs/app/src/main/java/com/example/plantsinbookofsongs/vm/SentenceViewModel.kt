package com.example.plantsinbookofsongs.vm

import android.app.Application
import android.content.res.Resources
import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.plantsinbookofsongs.R
import kotlin.random.Random


class SentenceViewModel(application: Application): AndroidViewModel(application =application){
    private val resources: Resources = getApplication<Application>().resources
    private val sentences: Array<String> = resources.getStringArray(R.array.Sentences)
    private val urls:Array<String> = resources.getStringArray(R.array.PlantImage)
    private val names:Array<String> = resources.getStringArray(R.array.PlantName)
    private val Intro:Array<String> = resources.getStringArray(R.array.PlantIntro)
    //或者依赖注入
    private var _sentence= mutableStateOf("诗句")
    val sentence: State<String>
        get()=_sentence

    private var _plantName= mutableStateOf("植物名")
    val plantName: State<String>
        get()=_plantName

    private var _plantIntro= mutableStateOf("植物介绍")
    val plantIntro: State<String>
        get()=_plantIntro

    private var _sentenceNumber= mutableStateOf(0)
    val sentenceNumber: State<Int>
        get()=_sentenceNumber


    private var _plantImageUrl = mutableStateOf("")
    val plantImageUrl:State<String>
        get()=_plantImageUrl

    fun initializeSentence(){
        val randomNumber = Random.nextInt(21)
        _sentence.value= sentences[randomNumber]
        _sentenceNumber.value=randomNumber
        _plantImageUrl.value=urls[randomNumber]
        _plantIntro.value=Intro[randomNumber]
        _plantName.value=names[randomNumber]
    }

    fun changeSentence(){
        val randomNumber = Random.nextInt(21)
        _sentence.value= sentences[randomNumber]
        _sentenceNumber.value=randomNumber
        _plantImageUrl.value=urls[randomNumber]
        _plantIntro.value=Intro[randomNumber]
        _plantName.value=names[randomNumber]
    }
}