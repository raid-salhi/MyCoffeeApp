package com.example.mycoffeeapp.screens.orderScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.SubText
import kotlinx.coroutines.delay

@Composable
fun FinishedOrderScreen (navController: NavController){
    LaunchedEffect(key1 = true, block = {
        delay(2000)
        navController.popBackStack()
        navController.navigate(Routes.HomeScreen.name)
    })
   Column(
       modifier= Modifier
           .padding(25.dp)
           .background(White)
           .fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ) {
       Image(
           painter = painterResource(id = R.drawable.ordered),
           contentDescription = "ordered",
       )
       Text(
           modifier=Modifier.padding(top=32.dp,bottom=22.dp),
           text="Ordered",
           color = MainText,
           fontSize = 22.sp,
           fontFamily = FontFamily(Font(R.font.poppins_regular))
       )
       Text(
           modifier=Modifier.padding(bottom=20.dp),
           text="Alex, your order has been successfully placed.",
           color = SubText,
           fontSize = 14.sp,
           fontFamily = FontFamily(Font(R.font.poppins_regular))
       )
       Text(
           modifier=Modifier.padding(bottom=20.dp),
           text="The order will be ready today\n" +
                   "to 18:10 at the address\n" +
                   "Bradford BD1 1PR.",
           color = MainText,
           fontSize = 14.sp,
           fontFamily = FontFamily(Font(R.font.poppins_regular))
       )
       Text(
           modifier=Modifier.padding(bottom=20.dp),
           text="Submit your personal QR code\n" +
                   "at a coffee shop to receive an order.",
           color = SubText,
           fontSize = 14.sp,
           fontFamily = FontFamily(Font(R.font.poppins_regular))
       )
   }
}