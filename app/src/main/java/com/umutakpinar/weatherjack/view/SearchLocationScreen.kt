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

// bu ekranda arama yapılacak
// (isimle arama özelliği eklenmeli!) (Bu kısım varsayılan olarak gelmeli!)
//(Bu nedenle bunu isimleri de veritabanında mı tutarsın ay da bir api'den çekip mi alırsın bilmiyorum umarım bununla alakalı bir seçenek vardır open-meteo'da!)
//yoksa kaynakalrını incele hangi api'ye istek atıyormuş bir bak
// konuma göre arama özelliği eklenmeli (bu kısım opsiyonel)
//Eğer lokasyon bulunursa deatiled screene yönlendir! bulunamazsa buluhnamadı yazdır.
//bu ekrana geldiysen backbutton görünür olmalı!

@Composable
fun SearchLocationScreen(){
    Box(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(
            "SearchLocationScreen",
            modifier = Modifier.align(Alignment.Center)
        );
    }
}