package com.example.mycoffeeapp.screens.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.mycoffeeapp.model.Coffee
import com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.onPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, sharedViewModel: SharedViewModel){
    val listOfCoffee : List<Coffee> = listOf<Coffee>(
    Coffee("Americano", R.drawable.americano,5.00),
    Coffee("Cappuccino", R.drawable.cappuccino,3.00),
    Coffee("Latte", R.drawable.latte,3.30),
    Coffee("Flat White", R.drawable.flat_white,3.00),
    Coffee("Raf", R.drawable.raf,4.00),
    Coffee("Espresso", R.drawable.espresso,3.50),
    )
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
                .padding(top = it.calculateTopPadding() + 10.dp)
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
                    color = onPrimaryColor,
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(top = 18.dp, bottom = 25.dp) ,
                ){
                    items(listOfCoffee){
                        CoffeeCardItem(it,navController,sharedViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CoffeeCardItem(coffee: Coffee, navController: NavController, sharedViewModel: SharedViewModel) {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F8FB)),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                sharedViewModel.sendCoffee(coffee)
                navController.navigate(Routes.OrderScreen.name)
            }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().padding(20.dp)) {
            Image(
                painter = painterResource(id = coffee.image),
                contentDescription = "coffee img",
                modifier = Modifier.fillMaxSize(0.8f),
                contentScale = ContentScale.Fit
            )
            Text(
                text = coffee.name,
                fontSize = 14.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(top = 10.dp)
            )
            
        }
    }

}
