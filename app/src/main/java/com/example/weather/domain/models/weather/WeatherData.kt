package com.example.weatherapp

data class WeatherResponse(
    val current: CurrentWeather?,
    val hourly: HourlyWeather?
)

data class CurrentWeather(
    val time: String?,
    val temperature_2m: Double?,
    val wind_speed_10m: Double?,
    val relative_humidity_2m: Int?,
    val weather_code: String?,
    val is_day: Int?,
    val rain: String?,
)

data class HourlyWeather(
    val time: List<String>?,
    val temperature_2m: List<Double>?,
    val weather_code: List<Int>?,
)


