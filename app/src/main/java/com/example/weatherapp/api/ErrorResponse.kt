package com.example.weatherapp.api


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("Message")
    var message: String? = ""
)