package com.example.plantsinbookofsongs.screen.aiscreen

import android.Manifest
import android.content.ContentValues
import android.graphics.BitmapFactory
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantsinbookofsongs.utils.ControlPermission.Companion.checkCameraPermission
import com.example.plantsinbookofsongs.vm.PlantClassifyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream


fun log(msg: String) {
    Log.d(ContentValues.TAG, msg)
}
@Composable
fun AIScreen(){
    val context= LocalContext.current
    val viewModel: PlantClassifyViewModel = viewModel()
    val info= remember{
        viewModel.plantResult
    }
    val PlantImage= remember {
        viewModel.plantImage
    }
    val PlantDescription= remember {
        viewModel.plantDescription
    }
    val DescriptionButton= remember {
        viewModel.DescriptionButton
    }

    //相册识别
    val albumLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
        , onResult ={
            /*viewModel.getRecognitionResultByUri(it.toString())*/
            if(it!=null){
                val resolver = context.contentResolver
                var inputStream: InputStream? = null
                try {
                    inputStream = it.let { it1 -> resolver.openInputStream(it1) }
                    val bitmap= BitmapFactory.decodeStream(inputStream)
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.getRecognitionResultByImage(bitmap)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    // 关闭流以避免内存泄漏
                    inputStream?.close()
                }
            }else{
                log("uri is null")
            }
        }
    )

    //权限申请
    val permissionLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission() ,
        onResult = {
            if(it){
                log("have camera permission")
            }else{
                log("no camera permission")
            }
        })

    //拍照识别
    val cameraLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
        , onResult = {
            CoroutineScope(Dispatchers.Main).launch {
                if (it != null) {
                    viewModel.getRecognitionResultByImage(bitmap = it)
                }else{
                    log("bitmap is null")
                }
            }
        })

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "${info.value}", fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Spacer(modifier = Modifier.height(36.dp))
        }
        item {
            Image(
                bitmap = PlantImage.value.asImageBitmap(),
                contentDescription = "图像识别",
                modifier = Modifier
                    .border(2.dp, Color.LightGray, RectangleShape)
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            Button(
                onClick = { albumLauncher.launch(PickVisualMediaRequest()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "相册识别", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onClick = {
                    if(checkCameraPermission(context)){
                        log("have camera permission")
                    }else{
                        log("no camera permission, apply it")
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                    cameraLauncher.launch()
                }
            ) {
                Text(text = "拍照识别", fontSize = 20.sp)
            }
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    enabled = DescriptionButton.value,
                    onClick = {
                        viewModel.showDesciption()
                    }
                ) {
                    Text(text = "查询详细信息")
                }
                Text(
                    text ="${PlantDescription.value}",
                    modifier = Modifier.padding(10.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
        item { 
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}