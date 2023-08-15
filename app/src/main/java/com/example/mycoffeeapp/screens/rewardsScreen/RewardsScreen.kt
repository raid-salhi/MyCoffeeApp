package com.example.mycoffeeapp.screens.rewardsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.screens.orderScreen.SeparateLine
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen (navController: NavController) {
    val list = listOf("Americano","Latte","Raf","Flat White","Cappuccino")
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Rewards")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                )
            )
        },
        containerColor = Color.White,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding(), start = 25.dp, end = 25.dp)
                .fillMaxSize()
        ) {
            LoyaltyCard()
            RedeemCard(){
                navController.navigate(Routes.RedeemScreen.name)
            }
            Text(
                text = "History Rewards",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                modifier = Modifier.padding(top = 15.dp, start = 5.dp)
            )
            LazyColumn(Modifier.padding(top = 10.dp, start = 5.dp)){
                items(list){
                    HistoryRewards(it)
                    SeparateLine()
                }
            }
        }
    }
}

@Composable
fun HistoryRewards(coffee: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = coffee,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight.Bold,
                color = PrimaryColor,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = "24 June | 12:30",
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight.Medium,
                color = PrimaryColor.copy(0.3f),
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }
        Text(
            text = "+ 12 Pts",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight.Bold,
            color = PrimaryColor
        )
    }
}

@Composable
fun LoyaltyCard() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = PrimaryColor,
        modifier = Modifier
            .padding(bottom = 15.dp, top = 10.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, top = 15.dp, bottom = 15.dp)
        ) {
            Row(
                Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Loyalty Card",
                    color = IconColorSecondary,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "4/8",
                    color = IconColorSecondary,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    for (i in 1..4){
                        Icon(painter = painterResource(id = R.drawable.filled_cup), contentDescription = "loyalty", tint = IconColor)
                    }
                    for (i in 4..8){
                        Icon(painter = painterResource(id = R.drawable.norml_cup), contentDescription = "loyalty", tint = IconColorSecondary)
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun RedeemCard(points:Int=2750,onClick:()->Unit={}){
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = PrimaryColor,
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth()
    ){
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.beans_img),
                contentDescription = "beans",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(66.dp)
                    .padding(top = 15.dp, start = 15.dp)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "My Points",
                        color = IconColorSecondary,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = points.toString(),
                        color = IconColorSecondary,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Button(
                    onClick = { onClick() },
                    shape= RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  Color(0x30A2CDE9),
                        contentColor = IconColorSecondary
                    )
                ) {
                    Text(
                        text = "Redeem drinks",
                        color = IconColorSecondary,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

        }
    }
}
