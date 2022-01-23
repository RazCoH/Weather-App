package com.raz.weatherapp.objects

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.raz.weatherapp.R
import com.raz.weatherapp.models.CityWeatherData
import com.raz.weatherapp.views.activities.MainActivity
import com.raz.weatherapp.views.fragments.CitiesListFragment
import com.raz.weatherapp.views.fragments.CityFragment

object ScreensNavigation {

    fun pushMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    fun pushCitiesListFragment(activity: FragmentActivity) {
        val citiesListFragment =
            CitiesListFragment(object : CitiesListFragment.CitiesListFragmentListeners {
                override fun onCitySelected(item: CityWeatherData) {
                    pushCityFragment(activity,item)
                }

            })
        activity.supportFragmentManager.beginTransaction()
            .addToBackStack(citiesListFragment.javaClass.name)
            .setCustomAnimations(R.anim.enter_fragment_animation, R.anim.exit_fragment_animation)
            .replace(R.id.fragments_container, citiesListFragment)
            .commit()
    }

    fun pushCityFragment(activity: FragmentActivity,city:CityWeatherData) {
        val cityFragment = CityFragment(city)
        activity.supportFragmentManager.beginTransaction()
            .addToBackStack(cityFragment.javaClass.name)
            .setCustomAnimations(R.anim.enter_fragment_animation, R.anim.exit_fragment_animation)
            .replace(R.id.fragments_container, cityFragment)
            .commit()
    }
}