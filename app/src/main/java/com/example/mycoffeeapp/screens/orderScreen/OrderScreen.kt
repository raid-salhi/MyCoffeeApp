package com.example.mycoffeeapp.screens.orderScreen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.SecondaryColorFirst
import com.example.mycoffeeapp.ui.theme.SecondaryColorSecond
import com.example.mycoffeeapp.ui.theme.borderColor
import com.example.mycoffeeapp.ui.theme.onPrimaryColor
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController, sharedViewModel: SharedViewModel){

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Order")
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.buy),
                        contentDescription = "buy",
                        modifier = Modifier.size(26.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = White,
                actionIconContentColor = IconColor,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black
            )
        )
    }) {
        val coffee = sharedViewModel.coffee
        var switchCheck by remember {
            mutableStateOf(false)
        }
        var count by remember {
            mutableStateOf(1)
        }
        var ristrettoChoice by remember {
            mutableStateOf(false)
        }
        var orderPlace by remember {
            mutableStateOf(false)
        }
        var volume by remember {
            mutableStateOf(1)
        }
        var pickedTime by remember{
            mutableStateOf(LocalTime.NOON)
        }
        val formattedTime = remember {
            derivedStateOf {
                DateTimeFormatter.ofPattern("HH:mm").format(pickedTime)
            }
        }
        var price by remember {
            mutableStateOf(coffee!!.price)
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            Surface(
                Modifier
                    .fillMaxSize(),
                color = White
            ) {
                Column(
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxSize()
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        color = Color(0xFFF7F8FB),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center){
                            Image(
                                painter = painterResource(id = coffee!!.image),
                                contentDescription = "coffee img",
                                modifier = Modifier.size(150.dp),
                            )

                        }
                    }
                    Row(
                        Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextForm(text=coffee!!.name)
                        QuantityBox(count, onIncreaseCount = { count += 1 }, onDecreaseCount ={count =if (count<=1) 1 else count -1} )
                    }
                    SeparateLine()
                    Row(
                        Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextForm("Ristretto")
                        Row() {
                            CustomBox(title="One", clicked = !ristrettoChoice){
                                ristrettoChoice=false
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            CustomBox(title="Two", clicked = ristrettoChoice){
                                ristrettoChoice=true
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
                        TextForm(text="Onsite / Takeaway")
                        Row() {
                            IconButton(onClick = { orderPlace = false }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.onsite),
                                    contentDescription ="onsite",
                                    tint = if (orderPlace) IconColorSecondary else IconColor
                                )
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            IconButton(onClick = { orderPlace = true }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.takeaway),
                                    contentDescription ="takeaway",
                                    tint = if (!orderPlace) IconColorSecondary else IconColor
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
                        TextForm("Volume, ml")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                            ) {
                                IconButton(onClick = { volume = 1 }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.volume),
                                        contentDescription ="volume",
                                        tint = if (volume != 1)IconColorSecondary else IconColor ,
                                        modifier = Modifier
                                            .width(20.dp)
                                            .height(25.dp)
                                    )
                                }
                                Text(
                                    text = "250",
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = if (volume != 1)IconColorSecondary else IconColor
                                )
                            }

                            Spacer(modifier = Modifier.width(20.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                            ) {
                                IconButton(onClick = { volume=2 }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.volume),
                                        contentDescription ="volume",
                                        tint = if (volume != 2)IconColorSecondary else IconColor,
                                                modifier = Modifier
                                                    .width(25.dp)
                                                    .height(30.dp)
                                    )
                                }
                                Text(
                                    text = "350",
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = if (volume != 2)IconColorSecondary else IconColor
                                )
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                            ) {
                                IconButton(onClick = {volume=3 }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.volume),
                                        contentDescription ="volume",
                                        tint = if (volume != 3)IconColorSecondary else IconColor ,
                                        modifier = Modifier
                                            .width(30.dp)
                                            .height(35.dp)
                                    )
                                }
                                Text(
                                    text = "450",
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    color = if (volume != 3)IconColorSecondary else IconColor
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
                        TextForm("Prepare by a certain time today?")

                        Switch(
                            checked =switchCheck ,
                            onCheckedChange = { switchCheck = it } ,
                            modifier = Modifier.size(width = 50.dp, height = 30.dp)
                        )
                    }
                    AnimatedVisibility(visible =switchCheck, modifier = Modifier.align(Alignment.End)) {
                        val dialogState = rememberMaterialDialogState()

                        MaterialDialog(
                            dialogState = dialogState,
                            buttons = {
                                positiveButton("Ok")
                                negativeButton("Cancel")
                            }
                        ) {

                            timepicker(
                                initialTime = LocalTime.MIDNIGHT,
                                title = "Pick a date",
                                is24HourClock = true
                            ) { time ->
                                pickedTime = time
                                Log.d("TAG", "OrderScreen: ${time.hour} ")
                            }

                        }
                        TimePickedText(formattedTime.value){
                            dialogState.show()
                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    AssemblageCard()

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
                    val ristrettoPrice=if (!ristrettoChoice) 0.00 else 0.50
                    val priceVolume=if (volume==2) 1.00 else if (volume==3) 1.50 else 0.00
                    price = (coffee!!.price+ristrettoPrice+priceVolume)*count
                    Text(
                        text = "$ $price",
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
                        color = White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.dmsans_medium)),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

    }
}

@Composable
fun TimePickedText(time: String?,onClick: () -> Unit) {
    Text(
        text = time!!,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .clickable { onClick() }
            .size(86.dp, 33.dp)
            .background(borderColor),
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.dmsans_medium)),
        color = Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun AssemblageCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        SecondaryColorFirst,
                        SecondaryColorSecond
                    )
                )
            )
    ) {
        Row(Modifier.padding(15.dp)) {
            Icon(painter = painterResource(id =R.drawable.filter ), contentDescription ="filter" )
            Text(
                text ="Coffee lover assemblage",
                fontSize = 14.sp,
                color = White,
                fontFamily = FontFamily(Font(R.font.dmsans_medium)),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Row{
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                tint = White,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}

@Composable
fun SeparateLine() {
    Box(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .background(color = borderColor))
}

@Composable
fun CustomBox(title: String,clicked:Boolean,onClick:()->Unit) {
    Surface(
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(1.2.dp, color = if (!clicked) borderColor else Black),
        color = White,
        modifier = Modifier
            .width(80.dp)
            .height(40.dp)
            .clickable {
                onClick()
            }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = title,
                color = MainText,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.dmsans_medium))
            )
        }
    }
}

@Composable
fun TextForm(text: String) {
    Text(
        text = text,
        color = MainText,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.dmsans_medium))
    )
}
@Composable
fun QuantityBox(count:Int,onIncreaseCount: () -> Unit,onDecreaseCount:()->Unit){

    Surface(
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(1.2.dp, color = borderColor),
        color = White,
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            IconButton(onClick = { onDecreaseCount() }) {
                Icon(painterResource(id = R.drawable.baseline_remove_24), contentDescription ="minus", tint = IconColor)
            }
            Text(
                text = count.toString(),
                fontSize = 16.sp,
                color = MainText
            )
            IconButton(onClick = {onIncreaseCount () }) {
                Icon(painterResource(id = R.drawable.baseline_add_24), contentDescription ="plus", tint = IconColor )
            }
        }
    }
}

