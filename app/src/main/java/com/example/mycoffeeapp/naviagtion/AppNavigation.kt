package com.example.mycoffeeapp.naviagtion

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import  com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.screens.homeScreen.HomeScreen
import com.example.mycoffeeapp.screens.signIn.SignIn
import com.example.mycoffeeapp.screens.signUp.SignUp
import com.example.mycoffeeapp.screens.splashScreen.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppNavigation(){

    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = if (navController.currentBackStackEntryAsState().value?.destination?.route !=Routes.SplashScreen.name) Color.White else Color.Black
    )
    NavHost(navController = navController, startDestination = Routes.HomeScreen.name){

        composable(route = Routes.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(route = Routes.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(route = Routes.SignInScreen.name){
            SignIn(navController = navController)
        }
        composable(route = Routes.SignUpScreen.name){
            SignUp(navController = navController)
        }
    }
}