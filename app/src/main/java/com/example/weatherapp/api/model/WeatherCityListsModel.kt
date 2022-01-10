package com.example.weatherapp.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherCityListsModel(
    @SerializedName("cod")
    var cod: String = "",
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("list")
    var list: List<CityLists> = listOf(),
    @SerializedName("message")
    var message: String = ""
): Parcelable