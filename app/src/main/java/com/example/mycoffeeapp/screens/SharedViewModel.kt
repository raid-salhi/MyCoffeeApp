package com.example.mycoffeeapp.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mycoffeeapp.model.Coffee

class SharedViewModel: ViewModel() {
    var coffee by mutableStateOf<Coffee?>(null)
    fun sendCoffee(newCoffee: Coffee){
        coffee=newCoffee
    }
}