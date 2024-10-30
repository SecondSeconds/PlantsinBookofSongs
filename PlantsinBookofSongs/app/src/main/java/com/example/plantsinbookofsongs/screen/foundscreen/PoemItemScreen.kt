package com.example.plantsinbookofsongs.screen.foundscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.plantsinbookofsongs.data.Category
import com.example.plantsinbookofsongs.data.Poem
import com.example.plantsinbookofsongs.destination.FoundDestination

@Composable
fun PoemItemScreen(poem: Poem, category: Category, navController: NavController){
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(FoundDestination.Poem.createRoute(poem.id, category.id))
            },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape= RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row{
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ){
                Text(text="${poem.name}", style = TextStyle.Default,fontSize=20.sp)
            }
        }
    }

}