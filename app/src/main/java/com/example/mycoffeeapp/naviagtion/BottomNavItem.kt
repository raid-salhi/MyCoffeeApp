package com.example.mycoffeeapp.naviagtion

import com.example.mycoffeeapp.R

sealed class BottomNavItem(var name : String, var icon :Int , var route : String){
    object  Home : BottomNavItem("Menu", R.drawable.shop,Routes.HomeScreen.name)
    object  Rewards : BottomNavItem("Rewards", R.drawable.rewards,Routes.RewardsScreen.name)
    object  Order : BottomNavItem("My Order", R.drawable.order,Routes.MyOrderScreen.name)
}


