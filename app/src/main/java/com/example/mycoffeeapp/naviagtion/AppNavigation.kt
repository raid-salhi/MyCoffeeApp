package com.example.mycoffeeapp.naviagtion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import  com.example.mycoffeeapp.naviagtion.Routes

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SplashScreen.name){
        composable(route = Routes.SplashScreen.name){

        }
        composable(route = Routes.HomeScreen.name){

        }
        composable(route = Routes.SignInScreen.name){

        }
        composable(route = Routes.SignUpScreen.name){

        }
    }
}