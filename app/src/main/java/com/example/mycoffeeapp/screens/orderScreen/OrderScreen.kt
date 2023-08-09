package com.example.mycoffeeapp.screens.orderScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.screens.SharedViewModel
import com.example.mycoffeeapp.ui.theme.IconColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController, sharedViewModel: SharedViewModel){
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Order")
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.buy),
                        contentDescription = "buy",
                        modifier = Modifier.size(26.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White,
                actionIconContentColor = IconColor,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black
            )
        )
    }) {
        val coffee = sharedViewModel.coffee
        LaunchedEffect(key1 = coffee, block = {
            delay(200)
        })
        Surface(
            Modifier
                .padding(it)
                .fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(25.dp)
                    .fillMaxSize()
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    color = Color(0xFFF7F8FB),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(contentAlignment = Alignment.Center){
                        Image(
                            painter = painterResource(id = coffee!!.image),
                            contentDescription = "coffee img",
                            modifier = Modifier.size(150.dp),
                        )

                    }
                }
            }
        }
    }
}