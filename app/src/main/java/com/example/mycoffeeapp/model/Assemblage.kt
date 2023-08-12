package com.example.mycoffeeapp.model

data class Assemblage(
    val barista: Barista,
    val coffeeType: Int,
    val coffeeSort: String="Custom",
    val roastingDegree: Int=1,
    val grindingDegree:Int=1,
    val milk : String="None",
    val syrup : String="None",
    val iceCubes : Int=1
)
