package com.example.mycoffeeapp.naviagtion

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.screens.homeScreen.HomeScreen
import com.example.mycoffeeapp.screens.orderScreen.OrderScreen
import com.example.mycoffeeapp.screens.signIn.SignIn
import com.example.mycoffeeapp.screens.signUp.SignUp
import com.example.mycoffeeapp.screens.splashScreen.SplashScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(){

    val sharedViewModel : SharedViewModel = viewModel()
    val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Routes.HomeScreen.name) {

            composable(route = Routes.SplashScreen.name) {
                SplashScreen(navController = navController)
            }
            composable(route = Routes.HomeScreen.name) {
                HomeScreen(navController = navController,sharedViewModel)
            }
            composable(route = Routes.SignInScreen.name) {
                SignIn(navController = navController)
            }
            composable(route = Routes.SignUpScreen.name) {
                SignUp(navController = navController)
            }
            composable(route = Routes.OrderScreen.name) {

                OrderScreen(navController = navController,sharedViewModel)
            }
        }

}