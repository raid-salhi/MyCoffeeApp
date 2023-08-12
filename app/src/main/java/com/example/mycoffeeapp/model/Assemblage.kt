package com.example.mycoffeeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assemblage(
    val barista: Barista?=null,
    val coffeeType: Int=0,
    val coffeeSort: String="Custom",
    val roastingDegree: Int=1,
    val grindingDegree:Int=1,
    val milk : String="None",
    val syrup : String="None",
    val iceCubes : Int=1
):Parcelable
