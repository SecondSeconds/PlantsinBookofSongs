package com.example.plantsinbookofsongs.nav

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.plantsinbookofsongs.destination.BottomDestination


@Composable
fun BottomNav(navController: NavController){
    val menuItems= listOf<BottomDestination>(
        BottomDestination.Meet,
        BottomDestination.Found,
        BottomDestination.AI,
        BottomDestination.User
    )
    BottomNavigation (
        contentColor = Color.White,
        backgroundColor = MaterialTheme.colorScheme.primary
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        menuItems.forEach {
            BottomNavigationItem(
                label = { Text(text = it.title)},
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                selected = currentRoute==it.route,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        modifier = Modifier.size(20.dp)
                            .height(25.dp),
                        contentDescription = it.title,
                    )
                }
            )
        }
    }
}














