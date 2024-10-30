package com.example.plantsinbookofsongs.screen.userscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.destination.UserDestination

@Composable
fun VisitorScreen(navController: NavHostController){
    LazyColumn {
        item {
            Spacer(
                modifier = Modifier
                    .height(30.dp)
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
                    .fillMaxWidth()
                    .padding(all = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.defaultuser), // 替换为你的图片资源ID
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(shape = CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.onSecondary, CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Username",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "User ID: 12345678",
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.usertext),
                        overflow = TextOverflow.Ellipsis,
//                        modifier = Modifier.fillMaxWidth()
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.width(80.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "发现",
                    modifier = Modifier
                        .align(
                            Alignment.CenterVertically
                        )
                        .clickable {
                            navController.navigate(UserDestination.Login.route){
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                )
            }
            Spacer(
                modifier = Modifier
                    .height(0.3.dp)
                    .background(Color.Gray)
                    .fillMaxWidth()
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .background(colorResource(id = R.color.usercontainer))
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
            Row (modifier = Modifier.padding(12.dp)){
                Text(text = "音乐", modifier = Modifier.padding(2.dp))
                Spacer(modifier = Modifier.width(280.dp))
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "音乐",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp)
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