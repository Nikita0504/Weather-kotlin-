package com.example.weather.di

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.remote.location.getLocation
import com.example.weather.data.remote.weather.RetrofitInstance
import com.example.weather.domain.models.location.LocationData
import com.example.weatherapp.WeatherResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val apiService = RetrofitInstance.api

    val weatherResponse = mutableStateOf<WeatherResponse?>(null)
    val isLoading = mutableStateOf(true)
    val errorMessage = mutableStateOf<String?>(null)
    val locationData = mutableStateOf<LocationData?>(null)
    val location = mutableStateOf<String?>("Unknown")

    fun checkAndFetchLocationWithData(context: Context) {
        errorMessage.value = null

        getLocation(context = context) { data ->
            if (data.latitude == 0.0 && data.longitude == 0.0) {
                errorMessage.value = "Unable to fetch location. Please check permissions."
            } else {
                locationData.value = data
                location.value = data.location
                getWeather(data.latitude!!, data.longitude!!)
            }
        }
    }

    fun getWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            errorMessage.value = null
            try {
                val response = apiService.getWeather(
                    latitude = latitude,
                    longitude = longitude,
                    current = "temperature_2m,relative_humidity_2m,is_day,weather_code,wind_speed_10m,rain",
                    hourly = "temperature_2m,weather_code",
                )
                weatherResponse.value = response
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}
