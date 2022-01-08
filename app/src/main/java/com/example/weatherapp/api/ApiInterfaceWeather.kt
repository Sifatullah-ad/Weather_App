package com.example.weatherapp.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterfaceWeather {

    @GET("find?lat=23.68&lon=90.35&cnt=50&appid=e384f9ac095b2109c751d95296f8ea76")
    suspend fun getCityListsWithWeather(): Response<>
}