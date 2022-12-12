package com.umutakpinar.weatherjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.umutakpinar.weatherjack.ui.theme.WeatherJackTheme
import com.umutakpinar.weatherjack.view.DetailedLocationScreen
import com.umutakpinar.weatherjack.view.FavoriteLocationScreen
import com.umutakpinar.weatherjack.view.SearchLocationScreen
import com.umutakpinar.weatherjack.view.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherJackTheme {
                val navController = rememberNavController() //navigate edebilmemiz için gerekli bunu her screen view'ına parametre olarak göndermelisin
                //val isTopBarBackButtonVisible = remember { mutableStateOf<Boolean>(false) }
                val scaffoldState = rememberScaffoldState() //eğer başka bir ekran scaffoldu amniplue edecekse bu durumda bunu parametre olarak gönder oradan kontrol edebilesin
                ////ilk yolu kullanacağımız için burada böyle coroutinescope açtık viewmodel'ler içerisindeki functionları suspend yapacağız
                val scope = rememberCoroutineScope()
                //ayrıca amimasyonlar için de kullanılıyor sanırım
                val showBackButton = remember{ mutableStateOf<Boolean>(false) }
                //Her şeyi kaplayan surface burası
                //Şu linkteki gibi bir şey tasarlayalım.
                //https://stackoverflow.com/questions/66828175/what-is-scaffold-jetpack-compose

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        topBar = {
                            JackTopBar(navController = navController,showBackButton = showBackButton)
                        },
                        bottomBar = {
                                    JackBottomBar(scaffoldState = scaffoldState, scope = scope)
                        },
                        drawerContent = {
                                        JackDrawer()
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        floatingActionButton = {
                            JackFloatingActionButton(navController)
                        }
                    ) {

                        Column(
                            modifier = Modifier.padding(paddingValues = it
                            )
                        ) {
                            //Burası scaffold içerisindeki column screenler burada çağıralacak burada bir tasarım yapmayacaksın artık!
                            //Çağıracağın composable screenler eğer viewmodele erişirken kendi scope'larını kullanamıyorsa bu durumda parametre olarak her birine yuakrıda oluşturmuş olduğum scope'u verebilirsin.

                            //Test area
                            Box(modifier = Modifier.fillMaxSize()){

                                NavHost(navController = navController, startDestination = "favorite_locations_screen"){

                                    //herhangi bir yere navigate edildiğinde ya da bir navigatio args değiştiğinde çalışır!
                                    val navigationStateChanged = navController.addOnDestinationChangedListener { _, _, _ ->
                                        showBackButton.value = navController.currentDestination!!.route != "favorite_locations_screen"
                                    }

                                    composable("favorite_locations_screen"){ //Burada parametre alam direkt room'dan çek favorileri
                                        FavoriteLocationScreen()
                                    }

                                    composable("detailed_location_screen"){
                                        DetailedLocationScreen()
                                    }

                                    composable("search_location_screen"){
                                        SearchLocationScreen()
                                    }

                                    composable("settings_screen"){
                                        SettingsScreen()
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
    fun JackTopBar(navController: NavController,showBackButton : MutableState<Boolean>){
        TopAppBar(
            title = {
               Box(modifier = Modifier.fillMaxSize()){
                   Text(text = "WeatherJack",
                       textAlign = TextAlign.Center,
                       fontFamily = FontFamily.Monospace,
                       fontSize = 24.sp,
                       modifier = Modifier.align(Alignment.Center) //Box içerisindeki itemlerin kendisini sıralamak için modifier'In align özelliğini değiştirmek gerek
                   )

                    //Topbar back button, eğer parametre doğruysa göster
                   if(showBackButton.value){
                       IconButton(
                           onClick = {
                                     navController.navigateUp()
                           },
                           modifier = Modifier.align(Alignment.CenterEnd)
                       ) {
                           Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = "Back Button" )
                       }
                   }

               }

            }
        )
    }

    @Composable
    fun JackBottomBar(scope : CoroutineScope,scaffoldState: ScaffoldState){
        BottomAppBar(
            cutoutShape = CircleShape
        ) {
            IconButton(onClick = {
                //toggle scaffold
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Sharp.Menu, contentDescription = "Menu Drawer")
            }
        }
    }

    @Composable
    fun JackFloatingActionButton(navController : NavController){
        ExtendedFloatingActionButton(onClick = {
            if(navController.currentDestination!!.route.toString() != "search_location_screen"){
                navController.navigate("search_location_screen")
            }
        }, text = {
            Icon(imageVector = Icons.Sharp.Search, contentDescription = "Search Weather Button")
        })
    }

    @Composable
    fun JackDrawer(){
        Surface(modifier = Modifier.padding(10.dp)) {
            Text(text = "This is drawer")
        }
    }


}




