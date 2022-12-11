package com.umutakpinar.weatherjack

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.umutakpinar.weatherjack.ui.theme.WeatherJackTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherJackTheme {
                val navController = rememberNavController() //navigate edebilmemiz için gerekli
                //val isTopBarBackButtonVisible = remember { mutableStateOf<Boolean>(false) }
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope() ////ilk yolu kullanacağımız için burası böyle viewmodel'ler içerisindeki functionları suspend yapacağız


                /*
                val viewModel: MainActivityViewModel = hiltViewModel()
                //Drawera aç kapa yaptırmak
                LaunchedEffect(key1 = Unit){
                    delay(600)
                    viewModel.toggleDrawer(scaffoldState).apply {
                        delay(800)
                        viewModel.toggleDrawer(scaffoldState)
                    }
                }
                */

                //Her şeyi kaplayan surface burası
                //Şu linkteki gibi bir şey tasarlayalım.
                //https://stackoverflow.com/questions/66828175/what-is-scaffold-jetpack-compose
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Column(
                            modifier = Modifier.padding(paddingValues = it
                            )
                        ) {

                        }

                    }


                }


            }
        }
    }
}




