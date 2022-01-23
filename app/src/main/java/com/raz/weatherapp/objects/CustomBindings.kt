package com.raz.weatherapp.objects

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingBuildInfo
import androidx.databinding.BindingMethod
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.raz.weatherapp.R
import com.raz.weatherapp.utils.Constants.Companion.DRIZZLE_FIRST_CHAR
import com.raz.weatherapp.utils.Constants.Companion.RAIN_FIRST_CHAR
import com.raz.weatherapp.utils.Constants.Companion.SNOW_FIRST_CHAR
import com.raz.weatherapp.utils.Constants.Companion.SUN_LAST_CHAR
import com.raz.weatherapp.utils.Constants.Companion.SUN_OR_CLOUDS_FIRST_CHAR
import com.raz.weatherapp.utils.Constants.Companion.THUNDERSTORM_FIRST_CHAR
import com.raz.weatherapp.utils.Constants.Companion.WIND_FIRST_CHAR
import java.text.SimpleDateFormat
import java.util.*

object CustomBindings {
    @JvmStatic
    @BindingAdapter("bind.matchIcon")
    fun getIconByWeatherId(image: ShapeableImageView?, id: String) {
        var drawableId = 0
        when {
            id.startsWith(RAIN_FIRST_CHAR) -> {
                drawableId = R.drawable.ic_rain
            }
            id.startsWith(DRIZZLE_FIRST_CHAR) -> {
                drawableId = R.drawable.ic_cloud_rain
            }
            id.startsWith(THUNDERSTORM_FIRST_CHAR) -> {
                drawableId = R.drawable.ic_lightning
            }
            id.startsWith(SNOW_FIRST_CHAR) -> {
                drawableId = R.drawable.ic_snow
            }
            id.startsWith(WIND_FIRST_CHAR) -> {
                drawableId = R.drawable.ic_wind
            }
            id.startsWith(SUN_OR_CLOUDS_FIRST_CHAR) -> {
                drawableId = if (id.endsWith(SUN_LAST_CHAR)) {
                    R.drawable.ic_sun
                } else {
                    R.drawable.ic_cloud
                }
            }
        }
        if (image != null && drawableId != 0) {
            Glide
                .with(image.context)
                .load(drawableId)
                .into(image)
        }
    }


    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    @BindingAdapter("bind.getTs")
    fun getTimeFromTs(text: TextView, ts: String) {
        try {
            val sdf = SimpleDateFormat("HH:MM")
            val netDate = Date(ts.toLong() * 1000)
            text.text = sdf.format(netDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}