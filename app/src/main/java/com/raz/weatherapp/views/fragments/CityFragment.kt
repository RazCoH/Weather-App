package com.raz.weatherapp.views.fragments

import android.view.View
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import com.raz.weatherapp.R
import com.raz.weatherapp.databinding.CityWeatherFragmentLayoutBinding
import com.raz.weatherapp.models.CityWeatherData

class CityFragment(private val city:CityWeatherData?) :BaseFragment() {

    private lateinit var cvCityWeather:CardView

    override fun initViews(view: View) {
        cvCityWeather = view.findViewById(R.id.cvCityWeather)
        startAnimation()
        view.let {
            DataBindingUtil.bind<CityWeatherFragmentLayoutBinding>(it).apply {
                this?.dataItem = city
                this?.lifecycleOwner = activity
            }
        }
    }

    override fun initListeners() {

    }

    override fun initTexts() {

    }

    override fun getContent(): Int {
        return R.layout.city_weather_fragment_layout
    }

    private fun startAnimation(){
        val animation = AnimationUtils.loadAnimation(activity, R.anim.fade_in_anim)
        cvCityWeather.animation = animation
    }
}