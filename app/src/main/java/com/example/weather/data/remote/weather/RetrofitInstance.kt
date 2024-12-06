package com.example.weather.data.remote.weather

import com.example.weather.data.mappers.MyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private
    const val BASE_URL = "https://api.open-meteo.com/v1/"
    val api: MyApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(MyApi::class.java)
    }
}