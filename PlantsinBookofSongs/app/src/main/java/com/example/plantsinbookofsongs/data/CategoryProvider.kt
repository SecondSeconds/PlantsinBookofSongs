package com.example.plantsinbookofsongs.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.plantsinbookofsongs.R


object CategoryProvider {
    val CourtList= listOf(
        Category(
            id=1,
            name = "周南"
        ),
        Category(
            id=2,
            name = "召南"
        ),
        Category(
            id=3,
            name = "邶风"
        ),
        Category(
            id=4,
            name = "鄘风"
        ),
        Category(
            id=5,
            name = "卫风"
        ),
        Category(
            id=6,
            name = "王凤"
        ),
        Category(
            id=7,
            name = "郑风"
        ),
        Category(
            id=8,
            name = "齐风"
        ),
        Category(
            id=9,
            name = "魏风"
        ),
        Category(
            id=10,
            name = "唐风"
        ),
        Category(
            id=11,
            name = "秦风"
        ),
        Category(
            id=12,
            name = "陈风"
        ),
        Category(
            id=13,
            name = "桧风"
        ),
        Category(
            id=14,
            name = "曹风"
        ),
        Category(
            id=15,
            name = "豳风"
        )
    )
    val HymnList= listOf(
        Category(
            id = 16,
            name = "小雅"
        ),
        Category(
            id = 17,
            name = "大雅"
        )
    )
    val EulogyList= listOf(
        Category(
            id = 18,
            name = "周颂"
        ),
        Category(
            id = 19,
            name = "鲁颂"
        ),
        Category(
            id = 20,
            name = "商颂"
        )
    )

    fun getCategory(categoryId:Int): Category? {
        for(category in CourtList){
            if(category.id==categoryId){
                return category
            }
        }
        for(category in HymnList){
            if(category.id==categoryId){
                return category
            }
        }
        for(category in EulogyList){
            if(category.id==categoryId){
                return category
            }
        }
        return null
    }
    @Composable
    fun getCategorySynopsis(categoryId: Int):String{
        when(categoryId){
            1->return stringResource(id = R.string.ZhouNan)
            2->return stringResource(id = R.string.ShaoNan)
            else->return "nothing"
        }
    }
}