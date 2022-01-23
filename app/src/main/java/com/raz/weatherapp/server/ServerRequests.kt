package com.raz.weatherapp.server

import com.raz.weatherapp.models.CityWeatherData
import com.raz.weatherapp.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerRequests {

    @GET("data/2.5/weather?")
    fun getWeatherByCityName(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Call<CityWeatherData>

    @GET("data/2.5/weather?")
    fun getWeatherByCoordinates(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Call<CityWeatherData>
}