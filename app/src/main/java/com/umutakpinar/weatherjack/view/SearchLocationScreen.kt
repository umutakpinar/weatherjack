package com.umutakpinar.weatherjack.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.umutakpinar.weatherjack.model.Result
import com.umutakpinar.weatherjack.viewmodel.SearchLocationViewModel

// bu ekranda arama yapılacak
// (isimle arama özelliği eklenmeli!) (Bu kısım varsayılan olarak gelmeli!)
//(Bu nedenle bunu isimleri de veritabanında mı tutarsın ay da bir api'den çekip mi alırsın bilmiyorum umarım bununla alakalı bir seçenek vardır open-meteo'da!)
//yoksa kaynakalrını incele hangi api'ye istek atıyormuş bir bak
// konuma göre arama özelliği eklenmeli (bu kısım opsiyonel)
//Eğer lokasyon bulunursa deatiled screene yönlendir! bulunamazsa buluhnamadı yazdır.
//bu ekrana geldiysen backbutton görünür olmalı!


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchLocationScreen(
    context: Context,
    navController: NavController,
    viewModel: SearchLocationViewModel = hiltViewModel()
){
    val searchResultList by remember { viewModel.searchResultsList }
    val errorMessage = remember { viewModel.errorMessage }
    val isLoading = remember { viewModel.isLoading }

    val isSearchBarFocused = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current //guncel focusu yonetebilmek icin


    val labelText = remember{ mutableStateOf<String>("Search city name ...") }
    val searchText = remember{ mutableStateOf<String>("") }

    LaunchedEffect(key1 = searchText.value){
        viewModel.searchLocationText(searchText.value)
    }

    val paddingValues = 20.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                focusManager.clearFocus()
            }
            .padding(paddingValues)
    ) {


        TextField(
            value = searchText.value,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(10.dp, CircleShape)
                .background(Color.White, CircleShape)
                .onFocusChanged {
                    if (it.isFocused) {
                        labelText.value = "Search"
                        isSearchBarFocused.value = true
                    } else {
                        labelText.value = "Search city name ..."
                        isSearchBarFocused.value = false
                    }
                },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }), //Enter'a basılınca klavyeyi kapatmak için
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
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
                if(text.count().toChar() != '\n'){ //girilen text çift boşluk değil ise ve girilen her char \n değil ise
                    searchText.value = text
                }
            }
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.LightGray,
        ) {
            if(isSearchBarFocused.value && searchText.value != ""){
                Row(
                    modifier = Modifier
                        .background(Color(224,224,224)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(isLoading.value){
                        Column(modifier = Modifier.padding(paddingValues/4),content = {
                            CircularProgressIndicator()
                        })
                    }
                    if(errorMessage.value.isNotEmpty()){
                        Column(modifier = Modifier.padding(paddingValues/4),content = {
                            Text(text = "Searching error...")
                        })
                    }
                    if(searchResultList != listOf<List<Result>>()){
                        SearchResultLazyList(searchResultList = searchResultList, navController = navController,context)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultLazyList(searchResultList : List<Result>,navController: NavController,context: Context){
    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(searchResultList){ item ->
            val itemIx = searchResultList.indexOf(item)
            SearchResultLazyListRow(item = item,navController,context,itemIx)
        }
    }
}

@Composable
fun SearchResultLazyListRow(
    item : Result,
    navController: NavController,
    context: Context,
    itemIx : Int, //çiftse rengi koyu tekse açık yap gibi kullanılabilir.
    ){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                //navController.navigate()
            }
            .padding(5.dp)
    )
    {
        //Image(painter = rememberAsyncImagePainter("https://hatscripts.github.io/circle-flags/flags/${item.countryCode.toString().lowercase()}.svg"), contentDescription = item.name.toString())
        AsyncImage(
            modifier = Modifier.size(50.dp,50.dp),
            model = ImageRequest.Builder(context)
                .data("https://hatscripts.github.io/circle-flags/flags/${
                    item.countryCode.toString().lowercase()
                }.svg")
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = item.name.toString()
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column() {
            Text(
                text = item.name.toString(),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
            Text(
                text = item.country.toString(),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }
    }
}