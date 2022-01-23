package com.raz.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raz.weatherapp.databinding.CityWeatherCellBinding
import com.raz.weatherapp.models.CityWeatherData


class CitiesAdapter(
    private val items: List<CityWeatherData>,
    private val listener: OnCitySelectedListener
) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val heroesListBinding = CityWeatherCellBinding.inflate(inflater, parent, false)
        return CityViewHolder(heroesListBinding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindData(items[position])
        holder.itemView.setOnClickListener {
            listener.onCitySelected(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CityViewHolder(private val binding: CityWeatherCellBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(city: CityWeatherData) {
            binding.dataItem = city
        }
    }

    interface OnCitySelectedListener {
        fun onCitySelected(city: CityWeatherData)
    }
}