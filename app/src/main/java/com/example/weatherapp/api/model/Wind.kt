package com.example.weatherapp.api.model


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    var deg: Int = 0,
    @SerializedName("speed")
    var speed: Int = 0
)