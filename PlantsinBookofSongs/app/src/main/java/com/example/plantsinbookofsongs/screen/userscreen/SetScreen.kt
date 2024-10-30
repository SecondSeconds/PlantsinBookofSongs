package com.example.plantsinbookofsongs.screen.userscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.android.car.ui.AlertDialogBuilder
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.destination.BottomDestination
import com.example.plantsinbookofsongs.destination.UserDestination
import com.example.plantsinbookofsongs.state.LoginManager

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetScreen(navController: NavController){
    val context= LocalContext.current
    Scaffold(topBar ={
        SetTopBar(){
            navController.popBackStack()
        }
    } ) {
        val isLogin= LoginManager
        val userData=isLogin.getUser()
        LazyColumn{
            item { 
                Spacer(
                    modifier = Modifier
                        .height(36.dp)
                        .background(color = colorResource(id = R.color.usercontainer))
                        .fillMaxWidth()
                )
            }
            item {
                Box{
                    Spacer(
                        modifier = Modifier
                            .height(120.dp)
                            .background(color = colorResource(id = R.color.usercontainer))
                            .fillMaxWidth()
                    )
                    Image(painter = painterResource(id = userData.UserImage),
                        contentDescription = "用户头像",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(80.dp)
                            .clip(shape = CircleShape)
                            .border(0.5.dp, Color.LightGray, CircleShape)
                    )
                }
            }
//        item {
//            Spacer(
//                modifier = Modifier
//                    .height(0.3.dp)
//                    .background(Color.Gray)
//                    .fillMaxWidth()
//            )
//        }
            item {
                Spacer(
                    modifier = Modifier
                        .height(0.3.dp)
                        .background(Color.Gray)
                        .fillMaxWidth()
                )
                Row(modifier = Modifier.padding(12.dp)){
                    Text(
                        text = "账号",
                        modifier = Modifier
                            .padding(2.dp)
                            .size(60.dp, 35.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "${userData.UserId}",
                        modifier = Modifier
                            .padding(2.dp)
                            .size(300.dp, 35.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "更改账号",
                        tint = Color.Gray ,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(0.3.dp)
                        .background(Color.Gray)
                        .fillMaxWidth()
                )
                Row (modifier = Modifier.padding(12.dp)){
                    Text(
                        text = "用户名",
                        modifier = Modifier
                            .padding(2.dp)
                            .size(60.dp, 35.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "${userData.UserName}",
                        modifier = Modifier
                            .padding(2.dp)
                            .size(300.dp, 35.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "更改用户名",tint = Color.Gray,
                        modifier = Modifier
                    )
                }
            }
//        item {
//            Spacer(
//                modifier = Modifier
//                    .height(0.3.dp)
//                    .background(Color.Gray)
//                    .fillMaxWidth()
//            )
//            Row (
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(45.dp)
//            ){
//                Text(
//                    text = "签名",
//                    color = Color.Gray,
//                    modifier = Modifier
//                )
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = "更改签名",
//                    tint = Color.LightGray
//                )
//            }
//        }
            item {
                Spacer(
                    modifier = Modifier
                        .height(0.3.dp)
                        .background(Color.Gray)
                        .fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .height(45.dp)
                        .background(color = colorResource(id = R.color.usercontainer))
                        .fillMaxWidth()
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(0.3.dp)
                        .background(Color.Gray)
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                ){
                    Text(
                        text = "退出登录",
                        modifier = Modifier
                            .padding(2.dp)
                            .clickable {
                                AlertDialogBuilder(context)
                                    .setTitle("确认退出登录？")
                                    .setMessage("您确定要退出登录吗？")
                                    .setPositiveButton("确认") { _, _ ->
                                        isLogin.saveLoginStatus(false)
                                        navController.navigate(BottomDestination.Visitor.route) {
                                            popUpTo(navController.graph.findStartDestination().id)
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                    .setNegativeButton("取消") { _, _ ->

                                    }
                                    .show()
                            }
                            .size(360.dp, 35.dp)
                            .align(Alignment.CenterVertically),
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "退出登录",
                        tint = Color.Gray,
                        modifier = Modifier
                    )
                }
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(0.3.dp)
                        .background(Color.Gray)
                        .fillMaxWidth()
                )
            }
        }
    }

}
@Composable
fun SetTopBar(onBackPressed:()->Unit = {}){
    TopAppBar(
        title = { Text(text = "用户个人信息", fontWeight = FontWeight.ExtraBold, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = {onBackPressed()}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
            }
        },
        backgroundColor = MaterialTheme.colorScheme.primary
    )
}