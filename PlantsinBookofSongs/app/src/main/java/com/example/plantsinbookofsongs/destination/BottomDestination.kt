package com.example.plantsinbookofsongs.destination

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.plantsinbookofsongs.R

sealed class BottomDestination(
    val route:String,
    val icon: Int,
    val title: String
){
    data object Meet:BottomDestination("meet", R.drawable.meetscreen,"遇见")
    data object Found:BottomDestination("found", R.drawable.foundscreen,"发现")
    data object AI:BottomDestination("ai", R.drawable.aiscreen,"偃师")
    data object User:BottomDestination("user",R.drawable.userscreen,"兰台")
    data object Visitor:BottomDestination("visitor", R.drawable.userscreen,"游览")
}