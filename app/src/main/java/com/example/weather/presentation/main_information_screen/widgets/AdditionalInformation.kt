package com.example.weather.presentation.main_information_screen.widgets

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.remote.getScreenHeight
import com.example.weather.data.remote.getScreenWidth
import com.example.weatherapp.CurrentWeather

@Composable
fun AdditionalInformation(currentWeather: CurrentWeather, context: Context){
    val screenHeight = getScreenHeight(context)
    val screenWidth = getScreenWidth(context)

    Card(modifier = Modifier
        .height((screenHeight * 0.06f).dp)
        .width((screenWidth * 0.35f).dp),
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
            bottomEnd = 24.dp,
            bottomStart = 24.dp,
        ),
        colors = CardDefaults.cardColors(Color(red = 50, green = 22, blue = 97, alpha = 255)),
        border = BorderStroke(1.5.dp, Color.Gray),
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AdditionalInformationCard(data = "${ currentWeather.rain.toString()}mm", id = R.drawable.rain, desk = "Rain")
            AdditionalInformationCard(data = "${ currentWeather.wind_speed_10m.toString()}km/h", id = R.drawable.wind, desk = "Wind speed")
            AdditionalInformationCard(data = "${ currentWeather.relative_humidity_2m}%", id = R.drawable.humidity, desk = "humidity")
        }
    }
}