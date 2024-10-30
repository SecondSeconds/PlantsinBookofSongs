package com.example.plantsinbookofsongs

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantsinbookofsongs.destination.BottomDestination
import com.example.plantsinbookofsongs.destination.FoundDestination
import com.example.plantsinbookofsongs.destination.UserDestination
import com.example.plantsinbookofsongs.nav.BottomNav
import com.example.plantsinbookofsongs.screen.aiscreen.AIScreen
import com.example.plantsinbookofsongs.screen.foundscreen.CategoryDetailScreen
import com.example.plantsinbookofsongs.screen.foundscreen.FoundScreen
import com.example.plantsinbookofsongs.screen.foundscreen.PoemDetailScreen
import com.example.plantsinbookofsongs.screen.meetscreen.MeetScreen
import com.example.plantsinbookofsongs.screen.userscreen.LoginScreen
import com.example.plantsinbookofsongs.screen.userscreen.RegisterScreen
import com.example.plantsinbookofsongs.screen.userscreen.SetScreen
import com.example.plantsinbookofsongs.screen.userscreen.StarScreen
import com.example.plantsinbookofsongs.screen.userscreen.UserScreen
import com.example.plantsinbookofsongs.screen.userscreen.VisitorScreen
import com.example.plantsinbookofsongs.state.LoginManager
import com.example.plantsinbookofsongs.ui.theme.PlantsInBookOfSongsTheme
import com.example.plantsinbookofsongs.utils.NavStatusViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface (tonalElevation = 0.dp){
               PlantsInBookOfSongsTheme {
                    val navController= rememberNavController()
                    MainScreen(navController = navController)
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController){
    val navViewModel:NavStatusViewModel= viewModel()
    var showNav= remember {
        navViewModel.shouldShowBottomNavigation
    }
    Scaffold (
        bottomBar = { if(showNav.value){ BottomNav(navController = navController) } },
        topBar = {},
        content = {
            NavHost(navController =navController, startDestination = "meet" ){
                composable(BottomDestination.Meet.route){
                    navViewModel.showBottomNavigation()
                    MeetScreen()
                }
                composable(BottomDestination.Found.route){
                    navViewModel.showBottomNavigation()
                    FoundScreen(navController = navController)
                }
                composable(BottomDestination.AI.route){
                    navViewModel.showBottomNavigation()
                    AIScreen()
                }
                composable(BottomDestination.User.route){
                    navViewModel.showBottomNavigation()
                    var isLgin = LoginManager
                    if(isLgin.getLoginStatus()){
                        UserScreen(navController = navController)
                    }else{
                        VisitorScreen(navController = navController)
                    }
                }

                composable(UserDestination.Visitor.route){
                    navViewModel.showBottomNavigation()
                    VisitorScreen(navController = navController)
                }
                composable(UserDestination.User.route){
                    navViewModel.showBottomNavigation()
                    UserScreen(navController = navController)
                }
                composable(UserDestination.Register.route){
                    navViewModel.hideBottomNavigation()
                    RegisterScreen(navController = navController)
                }
                composable(UserDestination.Login.route){
                    navViewModel.hideBottomNavigation()
                    LoginScreen(navController = navController)
                }
                composable(UserDestination.Set.route){
                    navViewModel.hideBottomNavigation()
                    SetScreen(navController = navController)
                }
                composable(UserDestination.Star.route){
                    navViewModel.hideBottomNavigation()
                    StarScreen(navController)
                }

                composable(FoundDestination.Found.route){
                    navViewModel.showBottomNavigation()
                    FoundScreen(navController = navController)
                }
                composable(FoundDestination.Detail.route){
                    navViewModel.hideBottomNavigation()
                    val categoryId=it.arguments?.getString("categoryId")
                    if(categoryId==null){
                        Toast.makeText(LocalContext.current,"id是必需的", Toast.LENGTH_SHORT)
                    }else{
                        CategoryDetailScreen(categoryId = categoryId.toInt(),navController)
                    }
                }
                composable(FoundDestination.Poem.route){
                    navViewModel.hideBottomNavigation()
                    val poemId=it.arguments?.getString("poemId")
                    val categoryId=it.arguments?.getString("categoryId")
                    if(poemId==null||categoryId==null){
                        Toast.makeText(LocalContext.current,"id是必需的", Toast.LENGTH_SHORT)
                    }else{
                        PoemDetailScreen(poemId = poemId.toInt(),categoryId=categoryId.toInt(), navController = navController)
                    }
                }
            }
        }
    )
}