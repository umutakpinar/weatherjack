package com.umutakpinar.weatherjack.dependencyinjection

import com.umutakpinar.weatherjack.repository.LocationRepository
import com.umutakpinar.weatherjack.repository.WeatherRepository
import com.umutakpinar.weatherjack.service.LocationAPI
import com.umutakpinar.weatherjack.service.WeatherAPI
import com.umutakpinar.weatherjack.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(api : WeatherAPI) = WeatherRepository(api)

    /*
    //Yukarıdaki işlem kafanı karıştırmasın direkt = koyarsan return değeri anlamına geliyor! Yani yuakrıdaki gibi yazmakla aşağıdaki aynı şeyler
    @Singleton
    @Provides
    fun provideWeatherRepository(api : WeatherAPI) : WeatherRepository {
        return  WeatherRepository(api)
    }
    */

    @Singleton
    @Provides
    fun provideWeatherAPI() : WeatherAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constans.BASE_URL)
            .build()
            .create(WeatherAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationRepository(api : LocationAPI) = LocationRepository(api)

    @Singleton
    @Provides
    fun provideLocationAPI() : LocationAPI{
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL_LOC)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationAPI::class.java)
    }
}