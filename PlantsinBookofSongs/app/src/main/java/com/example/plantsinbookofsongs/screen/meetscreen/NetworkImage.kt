package com.example.plantsinbookofsongs.screen.meetscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import com.example.plantsinbookofsongs.R

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier) {
//    val painter: Painter = rememberImagePainter(
//        data = url,
//        builder = {
//            // 你可以在这里配置加载选项，比如缓存策略、占位符等
//            crossfade(true)
//            placeholder(R.drawable.loading) // 设置一个占位符
//            error(R.drawable.loading) // 设置一个错误占位符
//        }
//    )

    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = url,
            contentDescription ="植物图片",
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .size(300.dp)
                .clip(RoundedCornerShape(5.dp))
                .align(Alignment.Center),
            contentScale = ContentScale.Crop, // 设置图片的缩放方式
        )
//        // 使用Image组件来显示图片
//        Image(
//            painter = painter,
//            contentDescription = "植物图片", // 提供一个内容描述（如果需要）
//            modifier = Modifier
//                .fillMaxWidth(0.9f)
//                .size(300.dp)
//                .clip(RoundedCornerShape(5.dp))
//                .align(Alignment.Center),
//            contentScale = ContentScale.Crop, // 设置图片的缩放方式
//        )
    }
}

