package com.umutakpinar.weatherjack.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutakpinar.weatherjack.model.Result
import com.umutakpinar.weatherjack.repository.LocationRepository
import com.umutakpinar.weatherjack.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {
    //Bunları var olarak tanımlamamn gerekirse değiştirmeyi unutma
    val searchResultsList = mutableStateOf<List<Result>>(listOf())
    val errorMessage = mutableStateOf<String>("")
    val isLoading = mutableStateOf(false)

    suspend fun searchLocationText(searchText : String){ //Bunu da mutable mı vermeli?
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getLocationData(searchText) //Bu bize Resource tipinde Location döndürdü
            when(result){
                is Resource.Succes -> {
                    val searchResults = result.data!!.results.mapIndexed{
                        index, resultItem ->
                        Result(
                            id = resultItem.id,
                            name = resultItem.name,
                            latitude = resultItem.latitude,
                            longitude = resultItem.longitude,
                            elevation = resultItem.elevation,
                            featureCode = resultItem.featureCode,
                            countryCode = resultItem.countryCode,
                            admin1Id = resultItem.admin1Id,
                            timezone = resultItem.timezone,
                            population = resultItem.population,
                            postcodes = resultItem.postcodes,
                            countryId = resultItem.countryId,
                            country = resultItem.country,
                            admin1 = resultItem.admin1
                        )
                    }
                    errorMessage.value = ""
                    isLoading.value = false
                    searchResultsList.value = searchResults
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }

            }
        }
    }
    /* TODO BURADA BİR FONKSİYON YAZ. BU FONKSİYON SEARCHLOCATION İÇERİSİNDE DE OALBİLİR. BEN HER TUŞA BASTIĞIMDA  */
    //BURASI BİTMEDİ BURADA İÇ İÇE ÇAĞIRILACAK BİR  SİSTEM KURMALISIN


}