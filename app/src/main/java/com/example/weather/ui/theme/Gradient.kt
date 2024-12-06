package com.example.weather.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val backgroundGradientV1 =  Brush.linearGradient(
    0.0f to Color(red =  80, green = 70, blue = 157, alpha = 255),
    500.0f to Color(red =  100, green = 61, blue = 103, alpha = 255),
    start = Offset.Zero,
    end = Offset.Infinite,
)