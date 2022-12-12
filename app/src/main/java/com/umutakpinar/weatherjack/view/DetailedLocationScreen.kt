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

//Burada seçilen konumun bilgileri aktarılacak. favorilere eklenirse aramayı roomDb'ye kaydet! Favoriden gelecek konumun kayedilme tarihini de kaydet
// verileri en son x dakika önce güncellendi şeklinde yazdırabilesin böylece
//Kişi yenile butonuna bastığında ise güncel verileri göstersin
//Burada sistem tarihinive saatini alıp şu anki hava durumunu göstermeli
//önerilerde bulunmalı x saat sonra yağmur yağacak vs gibi ama bu sonrasının işleri öncelikle yuakrıdakileri bir hallet
//Bu ekrana geldiysen back button görünür olmalı!

@Composable
fun DetailedLocationScreen(){
    Box(modifier = Modifier.fillMaxSize().padding(20.dp)){
        Text(
            "Detailed Location Screen",
            modifier = Modifier.align(Alignment.Center)
        );

    }
}