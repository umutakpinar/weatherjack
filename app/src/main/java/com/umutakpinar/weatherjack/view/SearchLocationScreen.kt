package com.umutakpinar.weatherjack.view

import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
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
fun SearchLocationScreen(
    navController: NavController
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val labelText = remember{ mutableStateOf<String>("Search city name ...") }
        val searchText = remember{ mutableStateOf<String>("") }

        TextField(
            value = searchText.value,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .shadow(10.dp, CircleShape)
                .background(Color.White, CircleShape)
                .onFocusChanged {
                    if (it.isFocused) {
                        labelText.value = "Search"
                    } else {
                        labelText.value = "Search city name ..."
                    }
                },
            shape = CircleShape,
            trailingIcon = {
                Icon(imageVector = Icons.Sharp.Search, contentDescription = "Search Field")
            },
            singleLine = true,
            maxLines = 1,
            label = {
                Text(text = labelText.value)
            },
            onValueChange = { text ->
                /*TODO BURAYI EDITLE İYİ OLMALI 5-6 HARF YAZDIKTAN SONRA TAKILIYOR*/
                if(text.count().toChar() != '\n'){ //girilen text çift boşluk değil ise ve girilen her char \n değil ise
                    searchText.value = text
                }
            }
        )




    }
}