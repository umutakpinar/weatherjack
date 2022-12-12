package com.umutakpinar.weatherjack.model

data class Location(
    val generationtime_ms: Double,
    val results: List<Result>
)