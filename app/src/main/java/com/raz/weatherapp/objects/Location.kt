package com.raz.weatherapp.objects

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.raz.weatherapp.utils.Constants

object Location {

    var userLat: Double? = null
    var userLon: Double? = null

    fun checkForLocationPermission(
        activity: AppCompatActivity,
        gpsIsNotEnableListener: GPSIsNotEnableListener
    ) {
        val locationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        val task = locationProviderClient.lastLocation
        if (ContextCompat.checkSelfPermission(
                activity.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                activity.applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (!isGPSEnabled(activity)) {
                gpsIsNotEnableListener.onGPSNotEnabled()
            } else {
                task.addOnSuccessListener {
                    if (it != null) {
                        userLat = it.latitude
                        userLon = it.longitude
                    }
                }
            }
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                ), Constants.LOCATION_REQUEST_CODE
            )
        }
    }

    private fun isGPSEnabled(activity: AppCompatActivity): Boolean {
        val locationManager =
            activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    interface GPSIsNotEnableListener {
        fun onGPSNotEnabled()
    }
}