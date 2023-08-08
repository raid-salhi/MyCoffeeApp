package com.example.mycoffeeapp.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.onPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color= Color.LightGray,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 14.sp
                            )){
                                append("Welcome!\n")
                            }
                            withStyle(style = SpanStyle(
                                color= MainText,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 18.sp
                            )){
                                append("Alex")
                            }
                        }
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.buy),
                            contentDescription = "buy",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "profile",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    actionIconContentColor = IconColor,
                )
            )
        },
        containerColor = Color.White,

    ) {
        Surface(
            modifier = Modifier
                .padding(top = it.calculateTopPadding()+10.dp)
                .fillMaxSize(),
            color = PrimaryColor,
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
            ) {
                Text(
                    text = "Select your coffee",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = onPrimaryColor
                )
            }
        }
    }
}