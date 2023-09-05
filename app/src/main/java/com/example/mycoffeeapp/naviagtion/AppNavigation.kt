package com.example.mycoffeeapp.naviagtion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycoffeeapp.model.Assemblage
import com.example.mycoffeeapp.model.Barista
import com.example.mycoffeeapp.screens.myOrderScreen.MyOrderScreen
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.screens.orderScreen.coffeeLoverAssemblage.BaristaScreen
import com.example.mycoffeeapp.screens.orderScreen.coffeeLoverAssemblage.CoffeeLoverAssemblage
import com.example.mycoffeeapp.screens.homeScreen.HomeScreen
import com.example.mycoffeeapp.screens.orderScreen.FinishedOrderScreen
import com.example.mycoffeeapp.screens.orderScreen.OrderScreen
import com.example.mycoffeeapp.screens.profileScreen.ProfileScreen
import com.example.mycoffeeapp.screens.rewardsScreen.RedeemScreen
import com.example.mycoffeeapp.screens.rewardsScreen.RewardsScreen
import com.example.mycoffeeapp.screens.signIn.SignIn
import com.example.mycoffeeapp.screens.signUp.SignUp
import com.example.mycoffeeapp.screens.splashScreen.SplashScreen
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.onPrimaryColor


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){

    val sharedViewModel : SharedViewModel = viewModel()
    val navController = rememberNavController()
    val bottomNavRoutes= listOf(Routes.HomeScreen.name,Routes.RewardsScreen.name,Routes.MyOrderScreen.name)
    Scaffold (
        bottomBar = {
            if (bottomNavRoutes.contains(navController.currentBackStackEntryAsState().value?.destination?.route)){
                MyBottomBar(navController)
            }
        }
            ) {
        val modifier=Modifier.padding(it)
        NavHost(navController = navController, startDestination = Routes.SplashScreen.name) {
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
                val assemblage = it.savedStateHandle.get<Assemblage>("assemblage")
                OrderScreen(navController = navController,sharedViewModel,assemblage)
            }
            composable(route = Routes.CoffeeLoverAssemblage.name) {
                val barista = it.savedStateHandle.get<Barista>("Barista")
                CoffeeLoverAssemblage(navController = navController,sharedViewModel,barista)
            }
            composable(route = Routes.BaristaScreen.name) {
                BaristaScreen(navController = navController,sharedViewModel)
            }
            composable(route = Routes.FinishedOrderScreen.name) {
                FinishedOrderScreen(navController = navController)
            }
            composable(route = Routes.ProfileScreen.name) {
                ProfileScreen(navController = navController)
            }
            composable(route = Routes.RewardsScreen.name) {
                RewardsScreen(navController = navController)
            }
            composable(route = Routes.RedeemScreen.name) {
                RedeemScreen(navController = navController)
            }
            composable(route=Routes.MyOrderScreen.name){
                MyOrderScreen(navController = navController)
            }
        }
    }



}
@Composable
fun MyBottomBar(navController: NavHostController) {
    Surface (
        modifier = Modifier
            .padding(start = 25.dp, bottom = 25.dp, end = 25.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        contentColor = onPrimaryColor,
        tonalElevation = 10.dp,
        shadowElevation = 10.dp
            ){
        BottomAppBar(
            containerColor = Color.White,
            contentColor = onPrimaryColor,
            modifier = Modifier
                .fillMaxWidth()

        ){
            val items = listOf(
                BottomNavItem.Home,
                BottomNavItem.Rewards,
                BottomNavItem.Order
            )
            val destination = navController.currentBackStackEntryAsState().value?.destination
            Row(
                modifier = Modifier
                    .padding(start = 40.dp, end = 40.dp, top = 20.dp, bottom = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEach {  item ->
                    val selected = item.route== destination?.route?.split("?")?.first()
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "icon",
                        tint = if (selected) PrimaryColor else onPrimaryColor,
                        modifier = Modifier
                            .size(22.dp)
                            .clickable {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }
                    )
                }
            }
        }

    }
}
