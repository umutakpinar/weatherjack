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

//donate özelliği ekle
//Veriler kaç dakikada bir oto güncellensin (min 15dk)
//bildirimleri aç kapat (bu ileriki zamanlarda yapıalcak bir şey yağmur geliyor paltonuzu unutmayın gibi , veya sabah 7'de bugün hava şu saate kadar şöyle olacak vs gibi..)
//Bir sürü hava durumu uygulaması var bizimiknin olayı bu olmalı! Sürekli öneri göstermeli! x gündür yağır alıyor ve hava sıcaklıkları ortalama xxx derece mantar toplama zamanı falan gibi...
//Belli tarihlerde işte kestane hasatı zamanı vs vs gibi şeyler yazmalı
//KOşu için iyi bir hava vs
//Balık tutmayı seviyorsanız solucan toplamak için iyi bir hava vs vs gibi...
//Çok fazla yağış alması bekleniyorsa etraf çamur omuş olabilir araziye giderken aracınızı gözden geçirin vs vs
//favorileri temizle

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(
            "Settings screen",
            modifier = Modifier.align(Alignment.Center)
        );

    }
}