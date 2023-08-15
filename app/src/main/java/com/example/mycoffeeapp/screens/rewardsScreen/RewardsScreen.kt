package com.example.mycoffeeapp.screens.rewardsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardsScreen (navController: NavController) {
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

        }
    }
}
@Preview
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
            Row(Modifier.padding( bottom = 10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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
                    modifier = Modifier.fillMaxWidth().padding(15.dp)
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
