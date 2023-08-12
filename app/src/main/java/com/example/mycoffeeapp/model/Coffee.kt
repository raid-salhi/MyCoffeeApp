package com.example.mycoffeeapp.model

import android.os.Parcelable
import com.example.mycoffeeapp.R
import kotlinx.parcelize.Parcelize

//@Parcelize
data class Coffee(
    val name:String,
    val image : Int,
    val price : Double
)


