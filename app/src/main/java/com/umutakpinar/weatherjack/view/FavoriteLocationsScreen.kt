package com.umutakpinar.weatherjack.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//başlangıçta burası açılacak. Başlangıçta favori konum yoksa ortadaki ara butonuna bas gibi bir yazı eklenebilir.
//ya da anlık konum ile görülebilir
//bu ekrana geldiysen back button görünür olmalı!

@Composable
fun FavoriteLocationScreen(){
    Box(modifier = Modifier.fillMaxSize().padding(20.dp)){
        Text(
            "No favorite locations please search some and add favorite!",
            modifier = Modifier.align(Alignment.Center)
        );
    }
}