package com.example.weatherapp.api.model


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country")
    var country: String = ""
)