package com.umutakpinar.weatherjack

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.umutakpinar.weatherjack.ui.theme.WeatherJackTheme
import com.umutakpinar.weatherjack.view.MainScreen
import com.umutakpinar.weatherjack.viewmodel.ScaffoldViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherJackTheme {
                val navController = rememberNavController()
                val isTopBarBackButtonVisible = remember { mutableStateOf<Boolean>(false) }
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val viewModel: ScaffoldViewModel = hiltViewModel()

                //Drawera aç kapa yaptırmak
                LaunchedEffect(key1 = Unit){
                    delay(600)
                    viewModel.toggleDrawer(scaffoldState).apply {
                        delay(800)
                        viewModel.toggleDrawer(scaffoldState)
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            CustomTopAppBar(navController,LocalContext.current,scaffoldState,isTopBarBackButtonVisible,scope,viewModel) },
                        modifier = Modifier.shadow(35.dp, RectangleShape), //Pek bi etkisi olmadi sanki
                        scaffoldState = scaffoldState,
                        drawerContent = {
                            //TODO DRAWER İÇİNDE favorilere eklenmiş konumlar bulunacak!
                        },
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(paddingValues = it)
                                .padding(10.dp)
                        ) {
                            NavHost(navController = navController, startDestination = "main_screen"){
                                composable("main_screen"){
                                    MainScreen(navController = navController, isTopBarBackButtonVisible)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CustomTopAppBar(
    navHostController: NavHostController,
    context: Context,
    scaffoldState: ScaffoldState,
    isBackButtonVisible : MutableState<Boolean>,
    scope : CoroutineScope,
    viewModel: ScaffoldViewModel
    ){
    TopAppBar(
    ){
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            IconButton(
                modifier = Modifier.alpha(if(isBackButtonVisible.value) 1.0f else 0f),
                onClick = {
                    if(isBackButtonVisible.value){ //Yani yalnizca görünür ise tiklanilabilir olsun
                        Toast.makeText(context,"Back Button Clicked",Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button")
            }
            Text(
                text = "WeatherJack!",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.align(CenterVertically)
            )
            IconButton(onClick = {
                /* TODO Burada tiklaninca appDrawer açmali */
                    scope.launch {
                        viewModel.toggleDrawer(scaffoldState)
                    }
            }) {
                Icon(imageVector = Icons.Filled.Menu , contentDescription = "Menu Drawer Button")
            }
        }
    }
}



