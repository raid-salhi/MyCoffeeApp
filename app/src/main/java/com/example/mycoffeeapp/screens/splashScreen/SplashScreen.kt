package com.example.mycoffeeapp.screens.splashScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.model.auth.AuthResult
import com.example.mycoffeeapp.naviagtion.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@Composable
fun SplashScreen(navController: NavController,splashScreenViewModel: SplashScreenViewModel= hiltViewModel()) {
    val context= LocalContext.current
    LaunchedEffect(key1 = true, block = {
        splashScreenViewModel.authResults.collect{result ->
            when(result){
                is AuthResult.Authorized ->{
                    navController.popBackStack()
                    navController.navigate(Routes.HomeScreen.name)
                }
                is AuthResult.Unauthorized ->{
                    navController.popBackStack()
                    navController.navigate(Routes.SignInScreen.name)
                }
                is AuthResult.UnknownError ->{
                    Toast.makeText(context, "Unknown Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    })
    Box(modifier =Modifier.fillMaxSize() ){
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "splash",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(0.5f)))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(0.5f)
                .align(alignment = Alignment.TopCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.coffee_logo),
                contentDescription = "logo",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight(0.4f)
            )
            Text(
                text = "Magic Coffee",
                fontSize = 64.sp,
                fontFamily = FontFamily(Font(R.font.reenie_beanie_regular)),
                textAlign = TextAlign.Center,
                color= Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
        

    }
}