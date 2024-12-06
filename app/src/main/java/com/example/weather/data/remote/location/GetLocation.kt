package com.example.weather.data.remote.location

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.weather.domain.models.location.LocationData
import com.google.android.gms.location.LocationServices
import java.util.Locale

fun getLocation(context: Context, callback: (LocationData) -> Unit) {
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    if (ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            100
        )
        return
    }

    fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude

            val geocoder = Geocoder(context, Locale.getDefault())
            try {
                val addressList = geocoder.getFromLocation(latitude, longitude, 1)
                val locationName = addressList?.firstOrNull()?.locality ?: "Unknown"
                callback(LocationData(latitude = latitude, longitude = longitude, location = locationName))
            } catch (e: Exception) {
                e.printStackTrace()
                callback(LocationData(latitude = latitude, longitude = longitude, location = "Unknown"))
            }
        } else {
            callback(LocationData(latitude = 0.0, longitude = 0.0, location = "Unknown"))
        }
    }.addOnFailureListener {
        callback(LocationData(latitude = 0.0, longitude = 0.0, location = "Unknown"))
    }
}