package com.example.mycoffeeapp.screens.rewardsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.model.Coffee
import com.example.mycoffeeapp.screens.orderScreen.CustomTopBar
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import kotlin.math.roundToInt

@Composable
fun RedeemScreen(navController: NavController){
    val list= listOf(
        Coffee("Americano", R.drawable.americano,1340.0),
        Coffee("Cappuccino", R.drawable.cappuccino,1340.0),
        Coffee("Latte", R.drawable.latte,1400.0)
    )
    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Redeem",
                navigationClick = {}
            )
        },
        contentColor = Color.White,
        containerColor = Color.White
    ) {
        LazyColumn(
            Modifier.padding(top = it.calculateTopPadding() +10.dp, start = 25.dp, end = 25.dp)
        ){
            items(list){
                RedeemRow(it)
            }
        }
    }
}

@Composable
fun RedeemRow(coffee: Coffee) {
    Row(
        Modifier
            .padding(bottom = 30.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Image(
                painter = painterResource(id = coffee.image),
                contentDescription = "coffee",
                modifier = Modifier.size(60.dp)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = coffee.name,
                    color = PrimaryColor,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Valid until 04.07.21",
                    color =  PrimaryColor.copy(0.3f),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
                contentColor = Color.White
            )
        ) {
            Text(
                text = coffee.price.roundToInt().toString()+" Pts",
                color =  Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
