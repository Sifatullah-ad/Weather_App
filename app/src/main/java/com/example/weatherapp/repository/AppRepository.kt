package com.example.weatherapp.repository

import com.example.weatherapp.api.ApiInterfaceWeather
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiInterfaceWeather: ApiInterfaceWeather) {

    suspend fun getCityListsWithWeather(lat: Double, lon: Double, cnt: Int, appid: String) = apiInterfaceWeather.getCityListsWithWeather(lat, lon, cnt, appid)
}