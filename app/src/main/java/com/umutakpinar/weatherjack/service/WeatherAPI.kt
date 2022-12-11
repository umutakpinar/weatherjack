package com.umutakpinar.weatherjack.service

import com.umutakpinar.weatherjack.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

//Example url
//https://api.open-meteo.com/v1/forecast?latitude=41.01&longitude=28.95&hourly=temperature_2m,relativehumidity_2m,dewpoint_
interface WeatherAPI {
    @GET("forecast")
    fun getWeatherData(
        @Query("latitude") latitude : String,
        @Query("longitude") longitude : String,
        @Query("hourly") hourly_attributes : String
        ) : Weather
}