package com.example.mycoffeeapp.screens.coffeeLoverAssemblage

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.model.BottomSheetItem
import com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.screens.orderScreen.CustomTopBar
import com.example.mycoffeeapp.screens.orderScreen.SeparateLine
import com.example.mycoffeeapp.screens.orderScreen.TextForm
import com.example.mycoffeeapp.ui.theme.ActiveBlue
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.SubText
import com.example.mycoffeeapp.ui.theme.borderColor
import com.example.mycoffeeapp.ui.theme.onPrimaryColor
import com.example.mycoffeeapp.utils.Constants
import kotlinx.coroutines.launch

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

        var openSheet by remember {
            mutableStateOf(false)
        }
        var grindingChoice by remember {
            mutableStateOf(false)
        }
        var roastingChoise by remember {
            mutableStateOf(1)
        }
        var iceChoice by remember {
            mutableStateOf(1)
        }
        val bottomSheetIem = remember {
            mutableStateOf(BottomSheetItem(list= emptyList(),question=""))
        }
        if (openSheet){
            MyBottomSheet(list = bottomSheetIem.value.list , bottomSheetIem.value.question) {
                openSheet=false
            }
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
                            valueRange = 0f..100f,
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
                        onClick = {
                            bottomSheetIem.value= BottomSheetItem(list = Constants.COFFEE_SORT_LIST, question = "What sort of coffee do you prefer?")
                            openSheet=true
                                  },
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
                        IconButton(onClick = { roastingChoise =1 }, modifier = Modifier.padding(start = 10.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.roasting) ,
                                contentDescription ="roasting",
                                tint = if(roastingChoise==1)IconColor else IconColorSecondary
                            )
                        }

                        Row(
                            Modifier
                                .padding(start = 10.dp)
                                .clickable { roastingChoise = 2 },
                        ) {
                            val roastColor = if (roastingChoise==2)IconColor else IconColorSecondary
                            Icon(
                                painter = painterResource(id = R.drawable.roasting) ,
                                contentDescription ="roasting",
                                tint = roastColor
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.roasting) ,
                                contentDescription ="roasting",
                                tint = roastColor
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .clickable { roastingChoise = 3 },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val roastColor = if (roastingChoise==3)IconColor else IconColorSecondary
                            Icon(
                                painter = painterResource(id = R.drawable.roasting) ,
                                contentDescription ="roasting",
                                tint = roastColor
                            )
                            Row() {
                                Icon(
                                    painter = painterResource(id = R.drawable.roasting) ,
                                    contentDescription ="roasting",
                                    tint = roastColor
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.roasting) ,
                                    contentDescription ="roasting",
                                    tint = roastColor
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { grindingChoice = false}) {
                            Icon(
                                painter = painterResource(id = R.drawable.grinding) ,
                                contentDescription ="next",
                                tint = if (!grindingChoice)IconColor else IconColorSecondary,
                                modifier = Modifier.size(21.dp,25.dp)
                            )
                        }
                        IconButton(onClick = { grindingChoice = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.grinding) ,
                                contentDescription ="next",
                                tint = if (grindingChoice)IconColor else IconColorSecondary,
                                modifier = Modifier.size(27.dp,32.dp)
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
                    TextForm(text="Milk")
                    Button(
                        onClick = {
                            bottomSheetIem.value= BottomSheetItem(list = Constants.MILK_LIST, question = "What milk do you prefer?")
                            openSheet=true
                        },
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
                        onClick = {
                            bottomSheetIem.value= BottomSheetItem(list = Constants.SYRUP_LIST, question = "What syrup do you prefer?")
                            openSheet=true
                                  },
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
                        IconButton(onClick = { iceChoice=1 }, modifier = Modifier.padding(start = 10.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ice) ,
                                contentDescription ="roasting",
                                tint = if (iceChoice==1)IconColor else IconColorSecondary
                            )
                        }

                        Row(
                            Modifier
                                .padding(start = 10.dp)
                                .clickable { iceChoice = 2 }) {
                            val iceColor = if (iceChoice==2) IconColor else IconColorSecondary
                            Icon(
                                painter = painterResource(id = R.drawable.ice) ,
                                contentDescription ="roasting",
                                tint = iceColor
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ice) ,
                                contentDescription ="roasting",
                                tint = iceColor
                            )
                        }
                        Column(modifier = Modifier
                            .padding(start = 10.dp)
                            .clickable { iceChoice = 3 },
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            val iceColor = if (iceChoice==3) IconColor else IconColorSecondary
                            Icon(
                                painter = painterResource(id = R.drawable.ice) ,
                                contentDescription ="roasting",
                                tint = iceColor
                            )
                            Row() {
                                Icon(
                                    painter = painterResource(id = R.drawable.ice) ,
                                    contentDescription ="roasting",
                                    tint = iceColor
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ice) ,
                                    contentDescription ="roasting",
                                    tint = iceColor
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

@Composable
fun SheetRow(text: String,isClicked:Boolean,onClick:(String)->Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick(text)
            }
            .padding(18.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = if (isClicked) ActiveBlue else MainText
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(list: List<String>,question:String,onDismiss:()->Unit) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val coroutineScope = rememberCoroutineScope()
    var clicked by remember {
        mutableStateOf(false)
    }
    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = { onDismiss() },
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.Transparent)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(13.dp))
                    .background(Color.White)
            ) {
                Text(
                    text = question,
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = SubText
                )
                LazyColumn(Modifier.fillMaxWidth()){
                    items(list){
                        SeparateLine()
                        SheetRow(it, isClicked = clicked){text ->
                            clicked = !clicked
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                onClick = { onDismiss()  },
                shape = RoundedCornerShape(13.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = "Cancel",
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )
            }

        }
    }
}