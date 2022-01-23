package com.raz.weatherapp.views.fragments

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raz.weatherapp.R
import com.raz.weatherapp.adapters.CitiesAdapter
import com.raz.weatherapp.models.CityWeatherData
import com.raz.weatherapp.objects.Location
import com.raz.weatherapp.utils.Constants.Companion.BANGKOK
import com.raz.weatherapp.utils.Constants.Companion.LONDON
import com.raz.weatherapp.utils.Constants.Companion.NEW_YORK
import com.raz.weatherapp.utils.Constants.Companion.PARIS
import com.raz.weatherapp.view_models.CityWeatherViewModel
import com.raz.weatherapp.view_models.ViewModelFactory

class CitiesListFragment(private val listeners: CitiesListFragmentListeners?) : BaseFragment() {

    private val viewModelFactory = ViewModelFactory()
    private lateinit var rvCities: RecyclerView
    private var citiesList = ArrayList<CityWeatherData>()
    private lateinit var vProgress: ProgressBar
    private var cities = arrayOf(LONDON, PARIS, NEW_YORK, BANGKOK)
    private lateinit var cityWeatherViewModel: CityWeatherViewModel

    override fun initViews(view: View) {
        rvCities = view.findViewById(R.id.rvCities)
        vProgress = view.findViewById(R.id.vProgress)
        cityWeatherViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[CityWeatherViewModel::class.java]
    }

    override fun initListeners() {

    }

    override fun initTexts() {

    }

    override fun getContent(): Int {
        return R.layout.cities_list_fragment_layout
    }

    override fun onResume() {
        super.onResume()
        initCitiesList()
    }
    private fun initCitiesList() {
        if (citiesList.isEmpty()) {
            loadCities()
        } else {
            renderData()
        }
    }

    private fun showLoader() {
        vProgress.visibility = View.VISIBLE
        rvCities.visibility = View.GONE
    }

    private fun hideLoader() {
        rvCities.visibility = View.VISIBLE
        vProgress.visibility = View.GONE
    }

    private fun loadCities() {
        showLoader()
        for (city in cities) {
            cityWeatherViewModel.sendGetCityWeatherByName(
                city,
                object : CityWeatherViewModel.RequestDoneListener {
                    override fun onDone(city: CityWeatherData?) {
                        if (city != null) {
                            citiesList.add(city)
                        }
                        if (citiesList.size == cities.size) {
                            loadUserLocationWeather()
                        }
                    }

                    override fun onFailed() {
                        hideLoader()
                        Toast.makeText(context, R.string.server_error, Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    private fun loadUserLocationWeather() {
        if (Location.userLat != null && Location.userLon != null) {
            cityWeatherViewModel.sendGetCityWeatherByLocation(
                Location.userLat.toString(),
                Location.userLon.toString(),
                object : CityWeatherViewModel.RequestDoneListener {
                    override fun onDone(city: CityWeatherData?) {
                        if (city != null) {
                            citiesList.add(city)
                            renderData()
                        }
                    }

                    override fun onFailed() {
                        hideLoader()
                        Toast.makeText(
                            context,
                            R.string.server_error_user_location,
                            Toast.LENGTH_SHORT
                        ).show()
                        renderData()
                    }
                })
        }else{
            renderData()
        }
    }

    private fun renderData() {
        hideLoader()
        initCitiesAdapter()
    }

    private fun initCitiesAdapter() {
        val adapter = CitiesAdapter(citiesList, object : CitiesAdapter.OnCitySelectedListener {
            override fun onCitySelected(city: CityWeatherData) {
                listeners?.onCitySelected(city)
            }

        })
        rvCities.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCities.adapter = adapter
    }

    interface CitiesListFragmentListeners {
        fun onCitySelected(item: CityWeatherData)
    }
}