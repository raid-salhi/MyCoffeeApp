package com.example.mycoffeeapp.componants

import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor

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
                    cursorColor = MainText
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
                interactionSource = interactionSource

            )
        }
        Box(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = if (selected) MainText else Color.LightGray))
    }


}
