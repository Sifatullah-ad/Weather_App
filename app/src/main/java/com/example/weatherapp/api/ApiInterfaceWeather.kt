package com.example.weatherapp.api

import com.example.weatherapp.api.model.WeatherCityListsModel
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaceWeather {

    @GET("find")
    suspend fun getCityListsWithWeather(
        @Query("lat")lat: Double,
        @Query("lon")lon: Double,
        @Query("cnt")cnt: Int,
        @Query("appid")appid: String
    ): NetworkResponse<WeatherCityListsModel, ErrorResponse>
}