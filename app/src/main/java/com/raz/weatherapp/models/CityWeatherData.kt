package com.raz.weatherapp.models

data class CityWeatherData(
    val weather:List<WeatherModel>?,
    val main:MainDataModel?,
    val sys:SysModel?,
    val name:String?
    )