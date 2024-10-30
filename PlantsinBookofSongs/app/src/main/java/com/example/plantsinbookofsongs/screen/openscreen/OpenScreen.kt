package com.example.plantsinbookofsongs.screen.openscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.plantsinbookofsongs.destination.BottomDestination
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen (navController: NavHostController){
    var startAnimation by remember{
        mutableStateOf(false)
    }
    val alphaAnim= animateFloatAsState(targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 3000)
    )
    LaunchedEffect(key1 = true){
        startAnimation=true
        delay(4000)
        navController.popBackStack()
        navController.navigate(BottomDestination.Meet.route)
    }
    Splash(alphaAnim.value)
}
@Composable
fun Splash(alpha:Float){
    Box(modifier = Modifier
        .background(if (isSystemInDarkTheme()) Color.Black else Color.Blue)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Icon(modifier= Modifier
            .size(120.dp)
            .alpha(alpha),
            imageVector = Icons.Default.Face,
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }
}
