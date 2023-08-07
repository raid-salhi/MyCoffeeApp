package com.example.mycoffeeapp.naviagtion

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import  com.example.mycoffeeapp.naviagtion.Routes
import com.example.mycoffeeapp.screens.signIn.SignIn
import com.example.mycoffeeapp.screens.splashScreen.SplashScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashScreen.name){
        composable(route = Routes.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(route = Routes.HomeScreen.name){

        }
        composable(route = Routes.SignInScreen.name){
            SignIn(navController = navController)
        }
        composable(route = Routes.SignUpScreen.name){

        }
    }
}