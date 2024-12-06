package com.example.weather.data.remote

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class CheckPermission(activity: ComponentActivity) {
    private val requestPermissionsLauncher =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val isGranted = permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] == true
            onPermissionResult(isGranted)
        }

    private lateinit var onPermissionResult: (Boolean) -> Unit

    fun checkLocationPermission(onPermissionResult: (Boolean) -> Unit) {
        this.onPermissionResult = onPermissionResult
        requestPermissionsLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}