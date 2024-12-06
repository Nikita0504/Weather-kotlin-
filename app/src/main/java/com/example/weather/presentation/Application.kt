package com.example.weather.presentation

import android.content.Context
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.weather.di.MainViewModel
import com.example.weather.presentation.main_information_screen.MainWeatherInformation

@Composable
fun Application(viewModel: MainViewModel, location: String, context: Context) {
    val currentWeather = viewModel.weatherResponse.value
    val errorMessage = viewModel.errorMessage.value
    when {
        errorMessage != null -> {
            Log.e("Weather_requset", "Hourly data is missing: $errorMessage")
            Text(text = "Error: $errorMessage")
        }

        currentWeather != null -> {
            Log.i(
                "Weather_requset",
                "current: ${currentWeather.current}; Location: $location"
            )
            MainWeatherInformation(currentWeather = currentWeather, location = location, context = context)
        }

        else -> {
            Text(text = "No weather data available.")
        }
    }
}