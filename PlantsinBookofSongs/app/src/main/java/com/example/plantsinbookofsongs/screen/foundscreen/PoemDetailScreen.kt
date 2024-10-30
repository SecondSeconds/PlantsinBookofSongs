package com.example.plantsinbookofsongs.screen.foundscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.data.PoemProvider


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PoemDetailScreen(poemId:Int,categoryId:Int,navController:NavController){
    val poem= PoemProvider.getPoem(poemId,categoryId)
    val info= PoemProvider.getPoemSynopsis(poemId = poemId, categoryId = categoryId)
    val infoWithPiYin=PoemProvider.getPoemSynopsisPinyin(poemId = poemId, categoryId = categoryId)
    var poemInfo= remember {
        mutableStateOf(info)
    }
    Scaffold (
        topBar = {
            if (poem != null) {
                PoemTopBar(
                    text = poem.name
                ){
                    navController.popBackStack()
                }
            }else{
                PoemTopBar(
                    text = "Poem"
                ){
                    navController.popBackStack()
                }
            }
        }
    ){
        LazyColumn (
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            item {
                Spacer(modifier = Modifier.height(65.dp))
                if (poem != null) {
                    Text(text = "${poem.name}", fontSize = 25.sp, fontWeight = FontWeight.Bold )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(text=poemInfo.value, fontSize = 20.sp, lineHeight = 30.sp)
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Spacer(modifier = Modifier.width(60.dp))
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clip(RectangleShape),
                        icon = { Icon(Icons.Filled.Check, contentDescription = "YuanWen") },
                        text = { Text("显示原文") },
                        onClick = {
                            poemInfo.value=info
                        }
                    )
                    Spacer(modifier = Modifier.width(40.dp))
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clip(RectangleShape),
                        icon = { Icon(Icons.Filled.CheckCircle, contentDescription = "PinYin") },
                        text = { Text("显示拼音") },
                        onClick = {
                            poemInfo.value=infoWithPiYin
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun PoemTopBar(text:String,onBackPressed:()->Unit = {}){
    TopAppBar(
        title = { androidx.compose.material.Text(text = "${text}", fontWeight = FontWeight.ExtraBold, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = {onBackPressed()}) {
                androidx.compose.material.Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
            }
        },
        backgroundColor = MaterialTheme.colorScheme.primary
    )
}
