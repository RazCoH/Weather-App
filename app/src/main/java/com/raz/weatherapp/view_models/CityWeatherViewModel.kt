package com.raz.weatherapp.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raz.weatherapp.models.CityWeatherData
import com.raz.weatherapp.server.RetrofitInstance
import com.raz.weatherapp.utils.Constants
import com.raz.weatherapp.utils.Constants.Companion.API_KEY
import com.raz.weatherapp.utils.Constants.Companion.METRIC
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityWeatherViewModel : ViewModel() {

    fun sendGetCityWeatherByName(
        cityName: String,
        requestDoneListener: RequestDoneListener?
    ) {
        viewModelScope.launch {
            try {
                val getCityWeather =
                    RetrofitInstance.getServerRequests.getWeatherByCityName(
                        cityName,
                        METRIC,
                        API_KEY
                    )
                getCityWeather.enqueue(object : Callback<CityWeatherData> {
                    override fun onResponse(
                        call: Call<CityWeatherData>,
                        response: Response<CityWeatherData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            requestDoneListener?.onDone(response.body())
                        }
                    }

                    override fun onFailure(call: Call<CityWeatherData>, t: Throwable) {
                        requestDoneListener?.onFailed()
                    }

                })
            } catch (e: Exception) {
                e.printStackTrace()
                requestDoneListener?.onFailed()
            }
        }
    }


    fun sendGetCityWeatherByLocation(
        lat: String, lon: String,
        requestDoneListener: RequestDoneListener?
    ) {
        viewModelScope.launch {
            try {
                val getCityWeather =
                    RetrofitInstance.getServerRequests.getWeatherByCoordinates(
                        lat, lon,
                        METRIC,
                        API_KEY
                    )
                getCityWeather.enqueue(object : Callback<CityWeatherData> {
                    override fun onResponse(
                        call: Call<CityWeatherData>,
                        response: Response<CityWeatherData>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            requestDoneListener?.onDone(response.body())
                        }
                    }

                    override fun onFailure(call: Call<CityWeatherData>, t: Throwable) {
                        requestDoneListener?.onFailed()
                    }

                })
            } catch (e: Exception) {
                e.printStackTrace()
                requestDoneListener?.onFailed()
            }
        }
    }

    interface RequestDoneListener {
        fun onDone(city: CityWeatherData?)
        fun onFailed()
    }
}