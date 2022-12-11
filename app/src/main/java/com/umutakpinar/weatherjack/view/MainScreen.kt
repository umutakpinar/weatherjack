package com.umutakpinar.weatherjack.view

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController,
    isTopBarBackButtonVisible : MutableState<Boolean>
){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column() {

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                    ,modifier = Modifier.padding(15.dp)) {

                    TextField(value = "0",
                        modifier = Modifier.padding(end = 10.dp)
                        .weight(1f),
                        label = {
                                Text(text = "Latitude :",
                                fontSize = 18.sp)
                        },
                        onValueChange = {
                        //TODO viewmodel'i kullanıp veriyi gönder search'e basıldığında
                    })

                    TextField(value = "0",
                        modifier = Modifier.padding(start = 10.dp)
                            .weight(1f),
                        label = {
                            Text(text = "Longitude :",
                                fontSize = 18.sp)
                        },
                        onValueChange = {
                        //TODO viewmodel'i kullanıp veriyi gönder search'e basıldığında
                    })

                }

                Button(modifier = Modifier.align(CenterHorizontally),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Search")
                }
            }

        }
    }
}

