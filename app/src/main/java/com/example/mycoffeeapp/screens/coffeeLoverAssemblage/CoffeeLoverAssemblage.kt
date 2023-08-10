package com.example.mycoffeeapp.screens.coffeeLoverAssemblage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.screens.orderScreen.CustomTopBar
import com.example.mycoffeeapp.screens.orderScreen.QuantityBox
import com.example.mycoffeeapp.screens.orderScreen.SeparateLine
import com.example.mycoffeeapp.screens.orderScreen.TextForm
import com.example.mycoffeeapp.ui.theme.ActiveBlue
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.borderColor
import com.example.mycoffeeapp.ui.theme.onPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeLoverAssemblage(navController: NavHostController, sharedViewModel: SharedViewModel) {
    Scaffold(topBar = {
        CustomTopBar("Coffee lover assemblage")
    }){
        val coffee = sharedViewModel.coffee!!
        var sliderValue by remember {
            mutableStateOf(0f)
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(it)){
            Column(
                Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
                    .padding(25.dp)
            ) {
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Select a barista")
                    IconButton(onClick = { navController.navigate(Routes.BaristaScreen.name) }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight ,
                            contentDescription ="next",
                            tint = IconColor
                        )
                    }
                }
                SeparateLine()
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Coffee type")
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        Slider(
                            value = sliderValue,
                            onValueChange ={sliderValue=it} ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp),
                            steps = 10,
                            colors = SliderDefaults.colors(
                                thumbColor = onPrimaryColor.copy(0.9f),
                                activeTrackColor = ActiveBlue,
                                inactiveTrackColor = borderColor,
                            )
                        )
                        Row(
                            Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text="Arabica",
                                color= onPrimaryColor,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.dmsans_medium))
                            )
                            Text(
                                text="Robusta",
                                color= onPrimaryColor,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.dmsans_medium))
                            )
                        }
                    }
                }
                SeparateLine()
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Coffee sort")
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = IconColor
                        )
                    ) {
                        TextForm(text="Select")
                    }
                }
                SeparateLine()
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Roasting")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 10.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.roasting) ,
                                contentDescription ="roasting",
                                tint = IconColor
                            )
                        }

                        Row(Modifier.padding(start = 10.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.roasting) ,
                                contentDescription ="roasting",
                                tint = IconColorSecondary
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.roasting) ,
                                contentDescription ="roasting",
                                tint = IconColorSecondary
                            )
                        }
                        Column(modifier = Modifier.padding(start = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.roasting) ,
                                contentDescription ="roasting",
                                tint = IconColorSecondary
                            )
                            Row() {
                                Icon(
                                    painter = painterResource(id = R.drawable.roasting) ,
                                    contentDescription ="roasting",
                                    tint = IconColorSecondary
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.roasting) ,
                                    contentDescription ="roasting",
                                    tint = IconColorSecondary
                                )
                            }
                        }
                    }

                }
                SeparateLine()
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Grinding")
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.grinding) ,
                            contentDescription ="next",
                            tint = IconColor,
                            modifier = Modifier.size(21.dp,25.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.grinding) ,
                            contentDescription ="next",
                            tint = IconColorSecondary,
                            modifier = Modifier.size(27.dp,32.dp)
                        )
                    }
                }
                SeparateLine()
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Milk")
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = IconColor
                        )
                    ) {
                        TextForm(text="Select")
                    }
                }
                SeparateLine()
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Syrup")
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = IconColor
                        )
                    ) {
                        TextForm(text="Select")
                    }
                }
                SeparateLine()
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Additives")
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight ,
                            contentDescription ="next",
                            tint = IconColor
                        )
                    }
                }
                SeparateLine()
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextForm(text="Roasting")
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 10.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ice) ,
                                contentDescription ="roasting",
                                tint = IconColor
                            )
                        }

                        Row(Modifier.padding(start = 10.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ice) ,
                                contentDescription ="roasting",
                                tint = IconColorSecondary
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ice) ,
                                contentDescription ="roasting",
                                tint = IconColorSecondary
                            )
                        }
                        Column(modifier = Modifier.padding(start = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.ice) ,
                                contentDescription ="roasting",
                                tint = IconColorSecondary
                            )
                            Row() {
                                Icon(
                                    painter = painterResource(id = R.drawable.ice) ,
                                    contentDescription ="roasting",
                                    tint = IconColorSecondary
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ice) ,
                                    contentDescription ="roasting",
                                    tint = IconColorSecondary
                                )
                            }
                        }
                    }

                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .align(Alignment.BottomCenter)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Total Amount",
                        color = MainText,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )

                    Text(
                        text = coffee.price.toString(),
                        color = MainText,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = "Next",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.dmsans_medium)),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}