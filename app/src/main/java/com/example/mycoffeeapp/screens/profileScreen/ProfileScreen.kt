package com.example.mycoffeeapp.screens.profileScreen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.screens.orderScreen.CustomTopBar
import com.example.mycoffeeapp.ui.theme.Background
import com.example.mycoffeeapp.ui.theme.MainText
import com.example.mycoffeeapp.ui.theme.PrimaryColor

@Composable
fun ProfileScreen(navController: NavController){
    Scaffold (
        topBar = {
            CustomTopBar(
                title = "Profile",
                navigationClick = { navController.popBackStack() }
            )
        },
        containerColor = Color.White,
        contentColor = Color.White
            ){
        Column(
            modifier=Modifier.padding(top=it.calculateTopPadding(), start = 33.dp, end = 33.dp)
        ) {
            ProfileRow(icon=R.drawable.profile,title="Alex",subText="Name",onClick={})
            ProfileRow(icon=R.drawable.smartphone,title="+375 33 664-57-36",subText="Phone Number",onClick={})
            ProfileRow(icon=R.drawable.message,title="Email",subText="adosmenesk@pm.me",onClick={})
            ProfileRow(icon=R.drawable.location,title="Bradford BD1 1PR",subText="Location",onClick={})
            Image(
                painter = painterResource(id = R.drawable.qr_code),
                contentDescription ="qr code" ,
                modifier = Modifier.padding(top =65.dp).align(Alignment.CenterHorizontally).size(250.dp)
            )
        }
    }
}

@Composable
fun ProfileRow(icon: Int, title: String, subText: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 25.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconHolder(icon = icon)
            Column(
                Modifier
                    .padding(start = 15.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = subText,
                    fontSize = 10.sp,
                    color = MainText.copy(0.3f),
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = MainText,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.edit) ,
            contentDescription = "edit",
            modifier = Modifier.clickable { onClick() },
            tint = PrimaryColor)
    }
}

@Composable
fun IconHolder(icon:Int){
    Surface(
        shape = CircleShape,
        color = Background,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(13.dp)
        ) {
            Icon(
                painter = painterResource(id = icon) ,
                contentDescription ="Icon" ,
                tint = PrimaryColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}