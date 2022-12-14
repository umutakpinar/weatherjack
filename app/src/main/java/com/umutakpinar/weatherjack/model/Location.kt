package com.umutakpinar.weatherjack.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("results")
    var results : List<Result> = listOf(),
    @SerializedName("generationtime_ms" )
    var generationtimeMs : Double? = null
)