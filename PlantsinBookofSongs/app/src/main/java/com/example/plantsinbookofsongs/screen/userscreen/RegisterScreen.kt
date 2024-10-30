package com.example.plantsinbookofsongs.screen.userscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.destination.BottomDestination
import com.example.plantsinbookofsongs.destination.UserDestination
import com.example.plantsinbookofsongs.state.RegisterManager
import com.example.plantsinbookofsongs.vm.RegisterViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController){
//context
    val context= LocalContext.current

    val enrollViewModel: RegisterViewModel = viewModel()
    val info= remember {
        enrollViewModel.info
    }
    var isRegister=RegisterManager
    var enrollName by remember {
        mutableStateOf("")
    }
    var enrollWord by remember {
        mutableStateOf("")
    }
    var confirmWord by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            RegisterTopBar(){
                navController.popBackStack()
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 标题
                Text(
                    text = "注册",
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier
                    .height(40.dp)
                )
                // 用户名输入字段
                OutlinedTextField(
                    value = enrollName,
                    onValueChange = {
                        enrollName=it
                    },
                    label = { Text("Username") },
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username icon") }
                )

                // 密码输入字段
                OutlinedTextField(
                    value = enrollWord,
                    onValueChange = {
                        enrollWord=it
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(),
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password icon") }
                )

                // 确认密码输入字段
                OutlinedTextField(
                    value = confirmWord,
                    onValueChange = {
                        confirmWord=it
                    },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(),
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password icon") }
                )

                Spacer(modifier = Modifier
                    .height(120.dp)
                )
                // 注册按钮
                Button(
                    onClick = {
                        enrollViewModel.updateUserName(enrollName)
                        enrollViewModel.updateEnrollWord(enrollWord)
                        enrollViewModel.updateConfirmWord(confirmWord)
                        enrollViewModel.addUser()
                        MainScope().launch {
                            delay(1000)
                            Toast.makeText(context,info.value, Toast.LENGTH_SHORT).show()
                            if(isRegister.getRegisterStatus()){
                                navController.navigate(UserDestination.Login.route){
                                    popUpTo(BottomDestination.Visitor.route)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }else{
                                Toast.makeText(context,"出现问题，未成功登录", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier,
                    //colors = ButtonDefaults.buttonColors(colorResource(id = R.color.registerButton))
                ) {
                    Text(text="注册")
                }

                // 你可以在这里添加更多的元素，如错误消息、条款和条件等。
            }
        }
    )
}
@Composable
fun RegisterTopBar(onBackPressed:()->Unit = {}){
    TopAppBar(
        title = { androidx.compose.material.Text(text = "注册", fontWeight = FontWeight.ExtraBold, color = Color.White) },
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