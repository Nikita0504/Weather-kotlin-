package com.example.weather.data.mappers

import com.example.weatherapp.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String,
        @Query("hourly") hourly: String
    ): WeatherResponse
}