package com.umutakpinar.weatherjack.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(): ViewModel() {
    suspend fun search(latitude: String,longitude : String){
        //aldığım latitude ve longiude değerlerini girip geriye bilgileri döndürmeliyim ancak aldığım bilgi boş da olabilir.
    }
}