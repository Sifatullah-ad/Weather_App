package com.example.weatherapp.api.model


import com.google.gson.annotations.SerializedName

data class CityLists(
    @SerializedName("dt")
    var dt: Int = 0,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("rain")
    var rain: String = "",
    @SerializedName("snow")
    var snow: String = "",
    @SerializedName("weather")
    var weather: List<Weather> = listOf()
)