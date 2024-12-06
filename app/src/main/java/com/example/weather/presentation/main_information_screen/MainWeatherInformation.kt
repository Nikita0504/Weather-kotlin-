package com.example.weather.presentation.main_information_screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.domain.models.weather.WeatherType
import com.example.weather.presentation.main_information_screen.widgets.AdditionalInformation
import com.example.weather.presentation.main_information_screen.widgets.HourlyList
import com.example.weatherapp.WeatherResponse

@Composable
fun MainWeatherInformation(currentWeather: WeatherResponse, location: String, context: Context){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val weatherType: WeatherType = WeatherType.fromWMO(currentWeather.current!!.weather_code!!.toInt())
        Text(
            text = weatherType.weatherDesc,
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight(600)
        )
        Image(
            modifier = Modifier.padding(25.dp).size(150.dp),
            painter = painterResource(id = weatherType.iconRes),
            contentDescription = weatherType.weatherDesc,
        )
        Text(
            text = "Last update: ${currentWeather.current!!.time.toString()}",
            color = Color.White,
            fontWeight = FontWeight(600)
        )
        Text(
            modifier = Modifier.padding(12.dp),
            text = "${currentWeather.current!!.temperature_2m!!.toInt()}°C",
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight(800)
        )
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = "Location: $location",
            color = Color.White,
            fontWeight = FontWeight(600)
        )
        AdditionalInformation(currentWeather = currentWeather.current, context = context)
        Row(
            modifier = Modifier.padding(top = 10.dp, bottom = 5.dp).fillMaxWidth().padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Today", fontSize = 19.sp, color = Color(204, 166, 0),fontWeight = FontWeight(800), )
        }
        HourlyList(hourlyWeather = currentWeather.hourly!!, context = context)
    }

}



