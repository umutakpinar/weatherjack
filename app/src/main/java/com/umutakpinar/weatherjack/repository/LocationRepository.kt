package com.umutakpinar.weatherjack.repository

import com.umutakpinar.weatherjack.model.Location
import com.umutakpinar.weatherjack.service.LocationAPI
import com.umutakpinar.weatherjack.util.Resource
import dagger.Provides
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LocationRepository @Inject constructor(
    private val api: LocationAPI
){
    suspend fun getLocationData(searchText : String) : Resource<Location> {
        val response = try{
            api.getLocationData(searchText)
        }catch (e : Exception){
            return Resource.Error(message = "Something gone wrong...\nIf you are a developer look at this : "+e.message)
        }
        return Resource.Succes(response)
    }
}