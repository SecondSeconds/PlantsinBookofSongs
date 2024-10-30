package com.example.plantsinbookofsongs.screen.meetscreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.vm.SentenceViewModel
import com.example.plantsinbookofsongs.vm.StarViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun MeetScreen(){
    val context= LocalContext.current
    val viewModel: SentenceViewModel = viewModel()
    val starViewModel: StarViewModel= viewModel()
    val info = remember {
        starViewModel.info
    }
    val sentence= remember {
        viewModel.sentence
    }
    val sentenceNumber= remember {
        viewModel.sentenceNumber
    }
    val imageUrl= remember {
        viewModel.plantImageUrl
    }
    val plantName= remember {
        viewModel.plantName
    }
    val plantIntro= remember {
        viewModel.plantIntro
    }
    viewModel.initializeSentence()//初始化
    BoxWithConstraints {
        val cardHeight=maxHeight*0.85f
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    item {
                        Text(
                            text = "${plantName.value}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 24.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(2.dp)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    // 显示每日一句的内容
                    item {
                        Text(
                            text = "${sentence.value}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .weight(1f)
                                .padding(2.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    item {
                        Text(
                            text = "${plantIntro.value}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .weight(1f)
                                .padding(2.dp)
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                    item {
                        NetworkImage(
                            url = "${imageUrl.value}",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(alignment = AbsoluteAlignment.Right)
                        ) {
                            // 分享、收藏、转发图标按钮
                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Absolute.Right,
                                verticalAlignment = Alignment.Bottom) {
                                IconButton(onClick = { /* 分享逻辑 */ }) {
                                    Icon(imageVector = Icons.Default.Share, contentDescription = "分享")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                IconButton(onClick = {

                                }) {
                                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "喜爱")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                IconButton(onClick = {
                                    MainScope().launch {
                                        starViewModel.addStar(sentenceNumber.value)
                                        delay(1000)
                                        Toast.makeText(context,info.value,Toast.LENGTH_SHORT).show()
                                    }
                                }) {
                                    Icon(imageVector = Icons.Default.Star, contentDescription = "收藏")
                                }
                                Spacer(modifier = Modifier.width(16.dp)) // 增加与“换一换”按钮之间的间隔

                                // “换一换”按钮
                                TextButton(
                                    onClick = {viewModel.changeSentence() },
                                    modifier = Modifier.weight(1f), // 使用权重来平均分配剩余空间
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.textButtonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = MaterialTheme.colorScheme.onSurface
                                    )
                                ) {
                                    Text(text = "换一换")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
