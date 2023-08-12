package com.example.mycoffeeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Barista(
    val name:String,
    val active:Boolean,
    val image : Int
):Parcelable
