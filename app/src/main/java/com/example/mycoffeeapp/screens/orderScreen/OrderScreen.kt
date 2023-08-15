package com.example.mycoffeeapp.screens.orderScreen

import android.os.Build
import android.util.Log
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.model.Assemblage
import com.example.mycoffeeapp.model.Order
import com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.screens.orderScreen.coffeeLoverAssemblage.MyBottomSheet
import com.example.mycoffeeapp.ui.theme.Background
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.SecondaryColorFirst
import com.example.mycoffeeapp.ui.theme.SecondaryColorSecond
import com.example.mycoffeeapp.ui.theme.borderColor
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import okio.blackholeSink
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    assemblage: Assemblage?
){

    Scaffold(topBar = {
        CustomTopBar("Order", actionIcon =  R.drawable.buy)
    })
    {
        val coffee = sharedViewModel.coffee
        val order =sharedViewModel.order!!

        var switchCheck by remember {
            mutableStateOf(!order.time.isNullOrBlank())
        }
        var openSheet by remember {
            mutableStateOf(false)
        }
        var count by remember {
            mutableStateOf(order.quantity)
        }
        var ristrettoChoice by remember {
            mutableStateOf(order.ristretto != 1)
        }
        var orderPlace by remember {
            mutableStateOf(order.place != "Onsite")
        }
        var volume by remember {
            mutableStateOf(if (order.volume==250) 1 else if (order.volume==350) 2 else 3)
        }
        var pickedTime by remember{
            mutableStateOf(if (order.time.isNullOrBlank()) LocalTime.NOON else dateFormatter(order.time))
        }
        val formattedTime = remember {
            derivedStateOf {
                DateTimeFormatter.ofPattern("HH:mm").format(pickedTime)
            }
        }
        var price by remember {
            mutableStateOf(order.totalPrice)
        }
        val ristrettoPrice=if (!ristrettoChoice) 0.00 else 0.50

        val priceVolume=if (volume==2) 1.00 else if (volume==3) 1.50 else 0.00

        if (openSheet){
            PaymentSheet(
                price = price,
                navController = navController,
            ) {
                openSheet=false
            }
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
                    AssemblageCard(){
                        sharedViewModel.sendOrder(
                            Order(
                                coffee = coffee!!,
                                quantity = count,
                                ristretto = if (!ristrettoChoice) 1 else 2,
                                place = if (!orderPlace) "Onsite" else "Takeaway",
                                volume = if (volume==1) 250 else if (volume==2) 350 else 450,
                                time = if (switchCheck) formattedTime.value else null,
                                totalPrice = price,
                                assemblage = assemblage?:Assemblage()
                            )
                        )
                        navController.navigate(Routes.CoffeeLoverAssemblage.name)
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

                    price = (coffee!!.price+ristrettoPrice+priceVolume)*count
                    Text(
                        text = "$ $price",
                        color = MainText,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
                Button(
                    onClick = { openSheet=true },
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

@RequiresApi(Build.VERSION_CODES.O)
fun dateFormatter(time: String): LocalTime? {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return LocalTime.parse(time,formatter)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(title: String,actionIcon:Int?=null,navigationClick :()->Unit={},actionClick :()->Unit={}) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { navigationClick()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        },
        actions = {
            if (actionIcon!=null){
                IconButton(onClick = { actionClick() }) {
                    Icon(
                        painter = painterResource(id =actionIcon),
                        contentDescription = "buy",
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = White,
            actionIconContentColor = IconColor,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black
        )
    )
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
fun AssemblageCard(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSheet(price:Double,navController: NavController,onDismiss:()->Unit){
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutine = rememberCoroutineScope()
    var selected by remember {
        mutableStateOf(1)
    }
    ModalBottomSheet(
        sheetState =bottomSheetState ,
        onDismissRequest = { onDismiss()},
        shape = RoundedCornerShape(35.dp),
        containerColor = White
    ) {
        Box(modifier =Modifier.background(Transparent) ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(start = 25.dp, end = 25.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Order Payment",
                    fontSize = 20.sp,
                    color = MainText,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                Spacer(modifier = Modifier.height(80.dp))
                Row(Modifier.padding(start = 8.dp)) {
                    Box(modifier = Modifier
                        .size(50.dp)
                        .background(Background)
                        .clip(RoundedCornerShape(18.dp))){
                        Icon(
                            painter = painterResource(id =R.drawable.buy ),
                            contentDescription = "buy",
                            tint = IconColor,
                            modifier = Modifier
                                .size(26.dp)
                                .align(Alignment.Center)
                        )
                    }
                    Column(
                        Modifier.padding(start = 20.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = "Alex",
                            fontSize = 12.sp,
                            color = MainText,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                        Text(
                            text = "Bradford BD1 1PR ",
                            fontSize = 10.sp,
                            color = MainText,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight.Light
                        )

                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                PaymentOptionRow(
                    title = "Online payment",
                    subtitle = "Assist Belarus",
                    image = R.drawable.payment1,
                    selected=selected,
                    option = 1,
                    modifier = Modifier.size(100.dp,30.dp)
                ){
                    selected=1
                }
                PaymentOptionRow(
                    title = "Credit Card",
                    subtitle = "2540 xxxx xxxx 2648",
                    image = R.drawable.payment2,
                    selected=selected,
                    option = 2,
                    modifier = Modifier.size(80.dp,20.dp)
                ){
                    selected=2
                }
                Spacer(modifier = Modifier.height(130.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(verticalArrangement = Arrangement.SpaceAround) {
                        Text(
                            text = "Total price",
                            fontSize = 12.sp,
                            color = MainText.copy(0.3f),
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                        Text(
                            text = "$ $price",
                            fontSize = 20.sp,
                            color = MainText,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        onClick = {
                            coroutine.launch { bottomSheetState.hide() }
                            navController.navigate(Routes.FinishedOrderScreen.name)
                                  },
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryColor,
                            contentColor = White
                        )
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                            Icon(painter = painterResource(id = R.drawable.card), contentDescription = "card", modifier = Modifier.padding(start = 30.dp))
                            Text(
                                text = "Pay now",
                                fontSize = 14.sp,
                                color = White,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp, end = 30.dp )
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun PaymentOptionRow(title: String,modifier: Modifier,subtitle:String,image :Int,selected:Int,option:Int,onClick: () -> Unit) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 10.dp, bottom = 10.dp),
        shape = RoundedCornerShape(12.dp),
        color = Background,
    ) {
        Row(
            Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                RadioButton(
                    selected = selected==option,
                    onClick = { onClick() },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = IconColor,
                        unselectedColor = IconColor
                    )
                )
                Column(
                    Modifier.padding(start = 20.dp),
                    verticalArrangement = Arrangement.SpaceAround) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        color = MainText,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                    Text(
                        text =subtitle,
                        fontSize = 10.sp,
                        color = MainText.copy(0.3f),
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    )
                }
            }
            Image(
                painter = painterResource(id = image),
                contentDescription = "payment",
                modifier = modifier,
                contentScale = ContentScale.Fit
            )
        }
    }
}
