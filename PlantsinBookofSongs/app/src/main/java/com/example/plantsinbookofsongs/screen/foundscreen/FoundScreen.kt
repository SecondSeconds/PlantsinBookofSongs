package com.example.plantsinbookofsongs.screen.foundscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.plantsinbookofsongs.R
import com.example.plantsinbookofsongs.data.CategoryProvider
import com.example.plantsinbookofsongs.vm.FoundPoemViewModel


@SuppressLint("ResourceAsColor")
@Composable
fun FoundScreen(navController: NavHostController){
    val foundViewModel:FoundPoemViewModel= viewModel()
    val FoundResult= remember{
        foundViewModel.result
    }
    var foundPoem= remember {
        mutableStateOf("")
    }
    val CourtList= CategoryProvider.CourtList
    val HymnList= CategoryProvider.HymnList
    val EulogyList= CategoryProvider.EulogyList
    LazyColumn(contentPadding =
    PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .background(Color.White, RectangleShape)
            ){
                OutlinedTextField(
                    value =foundPoem.value,
                    onValueChange = {
                        foundPoem.value=it
                    },
                    label = { Text("搜索") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "found icon",
                            modifier = Modifier.clickable {
                                foundViewModel.getFoundResult(foundPoem.value)
                            }
                        )
                    }
                )
                Text(
                    text = "${FoundResult.value}",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(5.dp)
                        .height(32.dp)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "风",
                color = Color(R.color.william),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp))
        }
        items(
            items = CourtList,
            key = {it.id},
            itemContent = {
                CategoryItemScreen(category = it,navController)
            }
        )
        item{
            Text(text = "雅",
                color = Color(R.color.william),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp))
        }
        items(
            items = HymnList,
            key={it.id},
            itemContent = {
                CategoryItemScreen(category = it,navController)
            }
        )
        item{
            Text(text = "颂",
                color = Color(R.color.william),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp))
        }
        items(
            items = EulogyList,
            key = {it.id},
            itemContent = {
                CategoryItemScreen(category = it,navController)
            }
        )
    }
}



