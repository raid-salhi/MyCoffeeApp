package com.example.mycoffeeapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mycoffeeapp.naviagtion.AppNavigation
import com.example.mycoffeeapp.ui.theme.MyCoffeeAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCoffeeAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                   AppNavigation()

                }
            }
        }
    }
}

