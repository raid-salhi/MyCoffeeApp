package com.example.mycoffeeapp.screens.myOrderScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.mycoffeeapp.model.Coffee
import com.example.mycoffeeapp.screens.orderScreen.SeparateLine
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.PrimaryColor
import com.example.mycoffeeapp.ui.theme.SubText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MyOrderScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "My Orders")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                )
            )
    },
        containerColor = Color.White,
        contentColor = Color.White
    ) {
        val pagerState = rememberPagerState(initialPage = 0)
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Tabs(pagerState)
            TabsContent(pagerState)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState, pageCount = 2) {
            page ->
        if (page==0) OnGoingPage() else HistoryPage()
    }
}

@Composable
fun MyOrderRow(isHistoryPage :Boolean, coffee: Coffee, date:String="24 June | 12:30 PM", onClick:()->Unit={}){
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 23.dp, bottom = 10.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        Column(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.Start) {
            Text(
                modifier=Modifier.padding(bottom=10.dp),
                text=date,
                color = SubText,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight.Bold
            )
            Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.filled_cup),
                    contentDescription ="cup",
                    tint = PrimaryColor.copy(0.5f),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    modifier=Modifier.padding(start =10.dp),
                    text=coffee.name,
                    color = PrimaryColor,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text="$ "+coffee.price.toString(),
                color = PrimaryColor.copy(0.6f),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight.Bold
            )

            AnimatedVisibility(modifier =Modifier.padding(top=5.dp),visible = isHistoryPage) {
                Button(
                    onClick = { onClick() },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = PrimaryColor),
                ) {
                    Text(
                        text="Order",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 0.dp,end=0.dp)
                    )
                }
            }
        }
    }
    SeparateLine()

}

@Composable
fun OnGoingPage() {
    val list = listOf(
        Coffee("Americano", R.drawable.americano,5.00),
        Coffee("Cappuccino", R.drawable.cappuccino,3.00),
        Coffee("Latte", R.drawable.latte,3.30),
    )
    LazyColumn(Modifier.padding(top=12.dp, start = 30.dp, end = 30.dp)){
        items(list){
            MyOrderRow(
                isHistoryPage = false,
                coffee = it,
            )
        }
    }
}

@Composable
fun HistoryPage() {
    val list = listOf(
        Coffee("Latte", R.drawable.latte,3.30),
        Coffee("Flat White", R.drawable.flat_white,3.00),
    )
    LazyColumn(Modifier.padding(top=12.dp, start = 30.dp, end = 30.dp)){
        items(list){
            MyOrderRow(
                isHistoryPage = true,
                coffee = it,
            ){

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "On going" ,
        "History"
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        containerColor = Color.White,
        indicator = {
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(currentTabPosition = it[pagerState.currentPage]),
                height = 2.dp,
                color = PrimaryColor
            )
        },
        divider = {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = IconColorSecondary
            )
        }
    ) {
        list.forEachIndexed { index, title ->
            Tab(
                selected =pagerState.currentPage==index ,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                          },
                text = {
                    Text(
                        text = title,
                        color = if (pagerState.currentPage==index) PrimaryColor else IconColorSecondary,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
            )
        }
    }
}
