package com.example.weather.presentation.main_information_screen.widgets

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.data.remote.getScreenHeight
import com.example.weather.data.remote.getScreenWidth
import com.example.weather.domain.models.weather.WeatherType
import com.example.weatherapp.HourlyWeather

@Composable
fun HourlyList(hourlyWeather: HourlyWeather, context: Context) {
    val screenHeight = getScreenHeight(context)
    val screenWidth = getScreenWidth(context)

    if (hourlyWeather.weather_code != null &&
        hourlyWeather.time != null &&
        hourlyWeather.temperature_2m != null) {

        LazyRow {
            items(hourlyWeather.weather_code.size) { index ->
                val weatherType = WeatherType.fromWMO(hourlyWeather.weather_code[index])

                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .height((screenHeight * 0.055f).dp)
                        .width((screenWidth * 0.09f).dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = CardDefaults.cardColors(Color(red = 50, green = 22, blue = 97, alpha = 255)),
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 15.dp).fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${hourlyWeather.time[index].subSequence(11, 16)}",
                            color = Color.White,
                            fontWeight = FontWeight(600)
                        )
                        Image(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(35.dp),
                            painter = painterResource(id = weatherType.iconRes),
                            contentDescription = weatherType.weatherDesc,
                        )
                        Text(
                            text = "${hourlyWeather.temperature_2m!![index]}°",
                            color = Color.White,
                            fontWeight = FontWeight(600)
                        )
                    }
                }
            }
        }
    } else {
        Text("Loading data or no data available", color = Color.White)
    }
}