package com.example.mycoffeeapp.screens.orderScreen.coffeeLoverAssemblage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.model.Barista
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.screens.orderScreen.CustomTopBar
import com.example.mycoffeeapp.screens.orderScreen.TextForm
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.SubText
import com.example.mycoffeeapp.ui.theme.borderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaristaScreen(navController: NavController,sharedViewModel: SharedViewModel){
    val baristas = listOf<Barista>(
        Barista("Andrey",true, R.drawable.andrey),
        Barista("Vector",true,R.drawable.victor),
        Barista("Vera",false,R.drawable.vera)
    )
    Scaffold(topBar = {
        CustomTopBar("Coffee lover assemblage")
    }){
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    top = it.calculateTopPadding() + 25.dp,
                    bottom = 25.dp,
                    start = 25.dp,
                    end = 25.dp
                )
        ){
            TextForm(text="Select a barista")
            LazyColumn(modifier = Modifier.padding(top = 15.dp)){
                items(baristas){barista ->
                    BaristaRow(barista){
                        navController.previousBackStackEntry?.savedStateHandle
                            ?.set(
                                "Barista",
                                barista
                            )
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@Composable
fun BaristaRow(barista: Barista,onClick:()->Unit) {
 Surface(
     modifier = Modifier
         .padding(bottom = 15.dp)
         .clickable {
                    onClick()
         }
     ,
     color = White
 ) {
     Surface (
         modifier = Modifier
             .shadow(
                 elevation = 5.dp,
                 spotColor = if (barista.active) Green.copy(0.3f) else Red.copy(0.3f),
                 shape = RoundedCornerShape(22.dp)
             )
             .fillMaxWidth()
             .padding(top = 10.dp, bottom = 10.dp),
         color = White,
         shape = RoundedCornerShape(22.dp)
     ){
         Row(
             verticalAlignment = Alignment.CenterVertically,
             horizontalArrangement = Arrangement.SpaceBetween,
             modifier=Modifier
                 .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 25.dp)
                 .fillMaxWidth()
         ) {
             Row(verticalAlignment = Alignment.CenterVertically) {
                 Image(
                     painter = painterResource(id = barista.image),
                     contentDescription ="barista Img",
                     contentScale = ContentScale.Crop,
                     modifier = Modifier.size(65.dp)
                 )
                 Column(
                     modifier = Modifier.padding(start = 16.dp),
                     horizontalAlignment = Alignment.CenterHorizontally,
                     verticalArrangement = Arrangement.SpaceBetween
                 ) {
                     Text(
                         text = barista.name,
                         fontFamily = FontFamily(Font(R.font.poppins_regular)),
                         fontSize = 16.sp,
                         color= MainText
                     )
                     Text(
                         text = "Barista",
                         fontFamily = FontFamily(Font(R.font.poppins_regular)),
                         fontSize = 14.sp,
                         color= SubText
                     )
                 }
             }
             Box(
                 modifier = Modifier
                     .clip(CircleShape)
                     .size(15.dp)
                     .background(if (barista.active) Green else Red)
             )
         }
     }
 }
}
