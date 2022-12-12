package com.umutakpinar.weatherjack.service

import com.umutakpinar.weatherjack.model.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationAPI {
    //example url for istanbul
    //https://geocoding-api.open-meteo.com/v1/search?name=istanbul
    @GET("search")
    fun getLocationData(
        @Query("name") searchText : String
    ) : Location
}