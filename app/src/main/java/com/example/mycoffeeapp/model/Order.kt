package com.example.mycoffeeapp.model

data class Order(
    val coffee: Coffee,
    val assemblage: Assemblage=Assemblage(),
    val quantity : Int = 1,
    val ristretto : Int = 1,
    val place : String = "Onsite",
    val volume : Int = 250,
    val time : String?=null,
    val totalPrice : Double = coffee.price,
    val timeOfOrder : String=""
)
