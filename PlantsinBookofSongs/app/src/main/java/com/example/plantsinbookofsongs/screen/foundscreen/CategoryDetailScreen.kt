package com.example.plantsinbookofsongs.screen.foundscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.data.Category
import com.example.plantsinbookofsongs.data.CategoryProvider
import com.example.plantsinbookofsongs.data.Poem
import com.example.plantsinbookofsongs.data.PoemProvider

@SuppressLint("ResourceAsColor", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryDetailScreen (categoryId:Int,navController: NavController){
    val category: Category? = CategoryProvider.getCategory(categoryId = categoryId)
    val info:String=CategoryProvider.getCategorySynopsis(categoryId)
    val poemList:List<Poem> = PoemProvider.getPoemList(categoryId)
    Scaffold (
        topBar = {
            if (category != null) {
                PoemTopBar(
                    text = category.name
                ){
                    navController.popBackStack()
                }
            }else{
                PoemTopBar(
                    text = "Category"
                ){
                    navController.popBackStack()
                }
            }
        }
    ){
        LazyColumn(contentPadding =
            PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ){
            item {
                Spacer(modifier = Modifier.height(60.dp))
                if (category != null) {
                    Text(text = "${category.name}",
                        color = Color(R.color.william),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp),
                        lineHeight = 48.sp
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                Text(text = info, fontSize = 28.sp, lineHeight = 36.sp, modifier = Modifier.padding(2.dp))
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(
                items = poemList,
                key = {it.id},
                itemContent = {
                    if (category != null) {
                        PoemItemScreen(poem = it,category=category, navController = navController)
                    }
                },
            )
        }
    }
}
@Composable
fun CategoryTopBar(text:String,onBackPressed:()->Unit = {}){
    TopAppBar(
        title = { androidx.compose.material.Text(text = "${text}", fontWeight = FontWeight.ExtraBold, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = {onBackPressed()}) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
            }
        },
        backgroundColor = MaterialTheme.colorScheme.primary
    )
}