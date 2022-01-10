package com.example.weatherapp.api.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityLists(
    @SerializedName("dt")
    var dt: Int = 0,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("rain")
    var rain: String = "",
    @SerializedName("coord")
    var coord: Coord = Coord(),
    @SerializedName("main")
    var mainPart: MainPart = MainPart(),
    @SerializedName("wind")
    var windInfo: WindInfo = WindInfo(),
    @SerializedName("snow")
    var snow: String = "",
    @SerializedName("weather")
    var weather: List<Weather> = listOf()
): Parcelable