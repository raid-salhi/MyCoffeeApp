package com.example.mycoffeeapp.componants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.borderColor
import com.example.mycoffeeapp.ui.theme.onPrimaryColor

@Composable
fun MyButton(action: () -> Unit,modifier: Modifier) {
    Button(
        onClick = { action() },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor,
            contentColor = Color.White
        ),
        modifier = modifier
            .size(64.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.arrow___right), contentDescription = "next", modifier = Modifier.fillMaxSize())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    placeHolder: String,
    keyboardOptions: KeyboardOptions,
    icon: Int,
    trailingIcon : Int? = null

    ) {
    var text by remember {
        mutableStateOf("")
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var selected by remember {
        mutableStateOf(false)
    }
    var visible by remember {
        mutableStateOf(false)
    }
    selected = interactionSource.collectIsFocusedAsState().value
    Column (
        modifier= Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "icon",
                tint= MainText,
                modifier = Modifier.padding(end=10.dp))
            Box(modifier = Modifier
                .width(1.dp)
                .height(20.dp)
                .background(color = if (selected) MainText else Color.LightGray))
            TextField(
                value = text,
                onValueChange ={
                    text=it
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    placeholderColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    cursorColor = MainText,
                    unfocusedTrailingIconColor = IconColor,
                    focusedTrailingIconColor = IconColor,
                    textColor = Color.Black
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = placeHolder,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 16.sp
                    )
                },
                keyboardOptions = keyboardOptions,
                interactionSource = interactionSource,
                trailingIcon={
                    if (trailingIcon != null){
                        Icon(
                            painter=painterResource(id = trailingIcon),
                            contentDescription = "toggle password",
                            modifier = Modifier.clickable {
                                visible=!visible
                            }
                        )
                    }
                },
                visualTransformation = if (!visible) VisualTransformation.None else PasswordVisualTransformation()

            )
        }
        Box(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = if (selected) MainText else Color.LightGray))
    }
}
@Preview
@Composable
fun QuantityBox(){
    var count by remember {
        mutableStateOf(1)
    }
    Surface(
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(1.2.dp, color = borderColor),
        color = Color.White,
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
                ){
            IconButton(onClick = { count -= 1 }) {
                Icon(painterResource(id = R.drawable.baseline_remove_24), contentDescription ="minus" )
            }
            Text(
                text = count.toString(),
                fontSize = 14.sp,
                color = MainText
            )
            IconButton(onClick = { count += 1 }) {
                Icon(painterResource(id = R.drawable.baseline_add_24), contentDescription ="plus" )
            }
        }
    }
}
