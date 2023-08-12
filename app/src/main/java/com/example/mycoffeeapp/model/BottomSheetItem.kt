package com.example.mycoffeeapp.model

data class BottomSheetItem(
    val list: List<String>,
    val question:String,
    val customChoice :String,
    val onRowClicked : (String) -> Unit

)
