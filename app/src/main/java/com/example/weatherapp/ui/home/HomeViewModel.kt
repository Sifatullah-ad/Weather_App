package com.example.weatherapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.model.WeatherCityListsModel
import com.example.weatherapp.repository.AppRepository
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {

    var latitude = 23.68
    var longitude = 90.35
    var count = 20
    var appId = "e384f9ac095b2109c751d95296f8ea76"

    fun getCityListsWithWeather(): MutableLiveData<WeatherCityListsModel> {

        val responseBody = MutableLiveData<WeatherCityListsModel>()

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCityListsWithWeather(latitude, longitude, count, appId)
            withContext(Dispatchers.Main) {
                when (response) {
                    is NetworkResponse.Success -> {
                        if (response.body != null) {
                            responseBody.value = response.body
                        }
                    }is NetworkResponse.ServerError -> {

                    }
                    is NetworkResponse.NetworkError -> {

                    }
                    is NetworkResponse.UnknownError -> {

                    }
                }
            }
        }

        return responseBody
    }

}