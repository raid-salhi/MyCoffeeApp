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
import com.example.mycoffeeapp.componants.MyButton
import com.example.mycoffeeapp.componants.MyTextField
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
                icon= R.drawable.lock,
                trailingIcon = R.drawable.show)
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
            MyButton(action = {},modifier = Modifier.align(Alignment.End))
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

