package com.umutakpinar.weatherjack.repository

import com.umutakpinar.weatherjack.model.Weather
import com.umutakpinar.weatherjack.service.WeatherAPI
import com.umutakpinar.weatherjack.util.Constans
import com.umutakpinar.weatherjack.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class WeatherRepository @Inject constructor(
    private val api : WeatherAPI
) {
    suspend fun getWeatherData(latitude : String,longitude : String) : Resource<Weather>{
        val response = try {
            api.getWeatherData(latitude = latitude,longitude = longitude, hourly_attributes = Constans.HOURLY_ATTRIBUTES);
        }catch (e : Exception){
            return Resource.Error("Error!")
        }

        return Resource.Succes(response)
    }
}