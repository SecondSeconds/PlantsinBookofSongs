package com.example.plantsinbookofsongs.screen.userscreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.plantsinbookofsongs.destination.BottomDestination
import com.example.plantsinbookofsongs.destination.UserDestination
import com.example.plantsinbookofsongs.state.LoginManager
import com.example.plantsinbookofsongs.vm.LoginViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController){
    //context
    val context= LocalContext.current
    var isLogin=LoginManager
    val loginViewModel: LoginViewModel = viewModel()
    val info= remember {
        loginViewModel.info
    }
    var userPassword by remember {
        mutableStateOf("")
    }
    var userId by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            LoginTopBar(){
                navController.navigate(UserDestination.Visitor.route){
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 标题
            Text(
                text = "欢迎回来！",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )
            // 用户id输入框
            OutlinedTextField(
                value = userId,
                onValueChange = {
                    userId=it
                },
                label = { Text("用户账号") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "user id icon") }
            )

            // 密码输入框
            OutlinedTextField(
                value = userPassword,
                onValueChange = { userPassword=it },
                label = { Text("密码") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password icon") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            // 登录按钮
            Button(
                onClick = {
                    //若userId不符合数字格式？
                    if(canConvertToLong(userId)){
                        loginViewModel.updateUserId(userId.toLong())
                        loginViewModel.updateUserPassword(userPassword)
                        loginViewModel.findUser()
                        MainScope().launch {
                            delay(1000)
                            //如何延迟
                            Toast.makeText(context,info.value, Toast.LENGTH_SHORT).show()
                            if(isLogin.getLoginStatus()){
                                navController.navigate(UserDestination.User.route){
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }else{
                        Toast.makeText(context,"输入Id不符合数字格式，请重新输入！", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("登录")
            }

            // 注册链接或按钮
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "还没有账号？",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "去注册",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                        .clickable {
                            navController.navigate(UserDestination.Register.route)
                        },
                    textDecoration = TextDecoration.Underline,
                    /*trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = stringResource(id = R.string.content_description_forward_arrow)
                        )
                    }*/

                )

            }
            Spacer(modifier = Modifier.width(16.dp))
//        Text(
//            text = "其他登录方式",
//        )
//        Spacer(modifier = Modifier.width(8.dp))
            //QQ、微信登录
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp)
//        ){
//            // 微信登录按钮
//            OutlinedButton(
//                onClick = { /* 处理微信登录点击事件 */ },
//                modifier = Modifier.weight(0.8f)
//            ) {
//                Text("微信登录")
//                Icon(painter = painterResource(id = R.drawable.weixin), contentDescription = "微信登录")
//                // 可以在这里添加微信图标
//            }
//
//            // 添加间距
//            Spacer(modifier = Modifier.width(8.dp))
//
//            // QQ登录按钮
//            OutlinedButton(
//                onClick = { /* 处理QQ登录点击事件 */ },
//                modifier = Modifier.weight(0.8f)
//            ) {
//                Text("QQ登录")
//                Icon(painter = painterResource(id = R.drawable.qq), contentDescription = "QQ登录")
//                // 可以在这里添加QQ图标
//            }
//        }
        }
    }
}
private fun canConvertToLong(str: String): Boolean {
    return try {
        str.toLong()
        true
    } catch (e: NumberFormatException) {
        false
    }
}
@Composable
fun LoginTopBar(onBackPressed:()->Unit = {}){
    TopAppBar(
        title = { androidx.compose.material.Text(text = "登录", fontWeight = FontWeight.ExtraBold, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = {onBackPressed()}) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White
                )
            }
        },
        backgroundColor = MaterialTheme.colorScheme.primary
    )
}