package com.raz.weatherapp.views.activities

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Paint
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.raz.weatherapp.R
import com.raz.weatherapp.utils.Constants.Companion.LOCATION_REQUEST_CODE
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.provider.Settings
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import com.raz.weatherapp.objects.Location
import com.raz.weatherapp.objects.ScreensNavigation


class MainActivity : AppCompatActivity() {

    private var pressedTime:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ScreensNavigation.pushCitiesListFragment(this)
    }

    override fun onResume() {
        super.onResume()
       Location.checkForLocationPermission(this,object :Location.GPSIsNotEnableListener{
           override fun onGPSNotEnabled() {
               showEnabledLocationDialog()
           }
       })
    }

    private fun showEnabledLocationDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.loaction_off_dialog_layout)
        val ivClose = dialog.findViewById<ImageView>(R.id.ivClose)
        val tvClose = dialog.findViewById<TextView>(R.id.tvClose)
        val tvSettings = dialog.findViewById<TextView>(R.id.tvGoToSettings)
        tvSettings.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        tvClose.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        ivClose.setOnClickListener {
            dialog.dismiss()
        }
        tvClose.setOnClickListener {
            dialog.dismiss()
        }
        tvSettings.setOnClickListener {
            val callGPSSettingIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(callGPSSettingIntent)
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onBackPressed() {
        if (this.supportFragmentManager.backStackEntryCount == 1) {
            if (pressedTime + 4000 > System.currentTimeMillis()) {
                super.onBackPressed()
                finish()
            } else {
                Toast.makeText(this, R.string.double_back_press_text, Toast.LENGTH_SHORT).show();
            }
            pressedTime = System.currentTimeMillis()
        }else{
            super.onBackPressed()
        }
    }
}