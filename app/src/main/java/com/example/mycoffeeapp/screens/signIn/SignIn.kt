package com.example.mycoffeeapp.screens.signIn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.newStringBuilder
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.ui.theme.PrimaryColor


@Composable
fun SignIn(navController: NavController){
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(45.dp)
            .background(Color.White)) {
            Text(
                text = "Sign In",
                fontSize = 28.sp,
                color = MainText,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                modifier = Modifier.padding(top=45.dp)
            )
            Text(
                text ="Welcome Back",
                fontSize = 18.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top=25.dp,bottom = 50.dp)
            )

            MyTextField("Email address",
                keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email),
                icon= R.drawable.message )
            MyTextField("Password",
                keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Password),
                icon= R.drawable.lock )
            Text(
                text ="Forgot Password?",
                fontSize = 14.sp,
                color = MainText,
                textDecoration = TextDecoration.Underline,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                modifier = Modifier
                    .padding(top = 25.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(140.dp))
            Button(
                onClick = { /*TODO*/ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.End)
            ) {
                Icon(painter = painterResource(id = R.drawable.arrow___right), contentDescription = "next", modifier = Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.height(140.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )){
                        append("New member? ")
                    }
                    withStyle(style = SpanStyle(
                        color = MainText,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )){
                        append("Sign up")
                    }
                } ,
                modifier = Modifier.clickable {
                    navController.navigate(Routes.SignUpScreen.name)
                }
            )
        }
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
        modifier=Modifier.fillMaxWidth(),
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
