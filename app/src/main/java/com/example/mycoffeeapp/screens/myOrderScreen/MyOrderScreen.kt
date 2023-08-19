package com.example.mycoffeeapp.screens.myOrderScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycoffeeapp.R
import com.example.mycoffeeapp.screens.orderScreen.CustomTopBar
import com.example.mycoffeeapp.ui.theme.IconColor
import com.example.mycoffeeapp.ui.theme.IconColorSecondary
import com.example.mycoffeeapp.ui.theme.PrimaryColor
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
fun MyOrderRow(){

}

@Composable
fun OnGoingPage() {
    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "going")
    }
}

@Composable
fun HistoryPage() {
    Column(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "History",)
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
