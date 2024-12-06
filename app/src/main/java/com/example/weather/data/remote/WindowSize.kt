package com.example.weather.data.remote

import android.content.Context
import android.util.DisplayMetrics

fun getScreenWidth(context: Context): Int {
    val displayMetrics = DisplayMetrics()
    (context.getSystemService(Context.WINDOW_SERVICE) as android.view.WindowManager).defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun getScreenHeight(context: Context): Int {
    val displayMetrics = DisplayMetrics()
    (context.getSystemService(Context.WINDOW_SERVICE) as android.view.WindowManager).defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}