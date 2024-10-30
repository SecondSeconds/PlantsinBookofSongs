package com.example.plantsinbookofsongs.screen.userscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.plantsinbookofsongs.helper.showLog
import com.example.plantsinbookofsongs.vm.ShowViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StarScreen(navController: NavController){
    val showViewModel:ShowViewModel= viewModel()
    val starList= remember{
        showViewModel.starList
    }
//    获取收藏列表
    showViewModel.getStarList()
    Scaffold(
        topBar = {
            StarTopBar(){
                navController.popBackStack()
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ){
            item {
                Text(text = "收藏列表",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(4.dp))
            }
//            item {
//                Button(onClick = { /*TODO*/ }) {
//
//                }
//            }
            items(
                items = starList,
                itemContent ={
                    StarItemScreen(starText = it.value)
                }
            )
        }
    }
}
@Composable
fun StarTopBar(onBackPressed:()->Unit = {}){
    TopAppBar(
        title = { androidx.compose.material.Text(text = "收藏列表", fontWeight = FontWeight.ExtraBold, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = {onBackPressed()}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
            }
        },
        backgroundColor = MaterialTheme.colorScheme.primary
    )
}