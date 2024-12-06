package com.example.weather.presentation.main_information_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Composable
fun AdditionalInformationCard(id:Int, desk:String, data:String){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier.padding(25.dp).size(30.dp),
            painter = painterResource(id = id),
            contentDescription = desk,
        )
        Text(
            text = data,
            color = Color.White,
            fontWeight = FontWeight(600)
        )
        Text(
            text = desk,
            color = Color.White,
            fontWeight = FontWeight(600)
        )
    }
}