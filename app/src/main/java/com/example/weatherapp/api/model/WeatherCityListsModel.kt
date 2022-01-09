package com.example.weatherapp.api.model


import com.google.gson.annotations.SerializedName

data class WeatherCityListsModel(
    @SerializedName("cod")
    var cod: String = "",
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("list")
    var list: List<CityLists> = listOf(),
    @SerializedName("message")
    var message: String = ""
)