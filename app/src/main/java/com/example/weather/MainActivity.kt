package com.example.weather

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weather.data.remote.CheckPermission
import com.example.weather.di.MainViewModel
import com.example.weather.presentation.Application
import com.example.weather.ui.theme.WeatherTheme
import com.example.weather.ui.theme.backgroundGradientV1

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val checkPermission by lazy {CheckPermission(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        checkPermission.checkLocationPermission { isGranted ->
            if (isGranted) {
                viewModel.checkAndFetchLocationWithData(context = this)
            } else {
                viewModel.errorMessage.value = "Permission denied. Unable to fetch location."
            }
        }

        setContent {
            WeatherTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundGradientV1),
                    contentAlignment = Alignment.Center
                ) {
                    if (viewModel.isLoading.value) {
                        CircularProgressIndicator()
                    } else {
                        Application(viewModel = viewModel, location = viewModel.location.value!!, context = this@MainActivity)
                    }
                }
            }
        }
    }

}