package com.example.mycoffeeapp.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.model.Coffee
import com.example.mycoffeeapp.model.Order

class SharedViewModel: ViewModel() {
    var coffee by mutableStateOf<Coffee?>(null)
    var order by mutableStateOf<Order?>(
        Order(Coffee("haha", R.drawable.americano,3.00)))
    fun sendCoffee(newCoffee: Coffee){
        coffee=newCoffee
    }
    fun sendOrder(newOrder: Order){
        order=newOrder
    }
}