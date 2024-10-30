package com.example.plantsinbookofsongs.data

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.example.plantsinbookofsongs.R

object PoemProvider {
    //周南
    val zhounanList= listOf<Poem>(
        Poem(
            id=1,
            name = "关雎"
        ),
        Poem(
            id = 2,
            name = "葛覃"
        )
        ,
        Poem(
            id = 3,
            name = "卷耳"
        ),
        Poem(
            id = 4,
            name = "樛木"
        ),
        Poem(
            id = 5,
            name = "螽斯"
        ),
        Poem(
            id = 6,
            name = "桃夭"
        ),
        Poem(
            id = 7,
            name = "兔罝"
        ),
        Poem(
            id = 8,
            name = "芣苢"
        ),
        Poem(
            id = 9,
            name = "汉广"
        ),
        Poem(
            id = 10,
            name = "汝坟"
        ),
        Poem(
            id= 11,
            name = "麟之趾"
        )
    )
    //召南
    val shaonanList= listOf<Poem>(
        Poem(
            id=1,
            name = "鹊巢"
        ),
        Poem(
            id=2,
            name = "采蘩"
        ),
        Poem(
            id=3,
            name = "草虫"
        ),
        Poem(
            id=4,
            name = "采苹"
        )
    )


    fun getPoemList(categoryId:Int):List<Poem>{

        if(categoryId==1){
            return zhounanList
        }else if(categoryId==2){
            return shaonanList
        } else{
            return listOf()
        }
    }
    fun getPoem(poemId:Int,categoryId: Int): Poem? {
        if(categoryId==1){
            for(poem in zhounanList){
                if(poemId==poem.id){
                    return poem
                }
            }
            return null
        }else{
            return null
        }
    }
    @Composable
    fun getPoemSynopsis(poemId: Int,categoryId: Int):String{
        when (categoryId){
            1-> {
                when (poemId){
                    //仅以关雎为例
                    1->{
                        return stringResource(id = R.string.GuanJu)
                    }
                    else->{
                        return ""
                    }

                }
            }
            else->{
                return " "
            }
        }
    }

    @Composable
    fun getPoemSynopsisPinyin(poemId: Int,categoryId: Int):String{
        when (categoryId){
            1-> {
                when (poemId){
                    //仅以关雎为例
                    1->{
                        return stringResource(id = R.string.GuanJuPinYin)
                    }
                    else->{
                        return ""
                    }

                }
            }
            else->{
                return " "
            }
        }
    }
}