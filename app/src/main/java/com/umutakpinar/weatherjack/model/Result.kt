package com.umutakpinar.weatherjack.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("longitude")
    var longitude: Double? = null,
    @SerializedName("elevation")
    var elevation: Int? = null,
    @SerializedName("feature_code")
    var featureCode: String? = null,
    @SerializedName("country_code")
    var countryCode: String? = null,
    @SerializedName("admin1_id")
    var admin1Id: Int? = null,
    @SerializedName("timezone")
    var timezone: String? = null,
    @SerializedName("population")
    var population: Int? = null,
    @SerializedName("postcodes")
    var postcodes   : ArrayList<String> = arrayListOf(),
    @SerializedName("country_id")
    var countryId: Int? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("admin1")
    var admin1: String? = null
)