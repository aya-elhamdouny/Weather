package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather.model.weather
import com.example.weather.viewmodel.WeatherViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.databinding.ItemTempBinding


class WeatherAdapter : ListAdapter<weather, WeatherAdapter.weatherViewHolder>(DiffCallback){


    class weatherViewHolder(private var binding : ItemTempBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: weather){
            binding.hour = weather.forecast.forecastday[3].hour[0]
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<weather>() {
        override fun areItemsTheSame(oldItem: weather, newItem: weather): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: weather, newItem: weather): Boolean {
            return  oldItem.location.region == newItem.location.region
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): weatherViewHolder {
        return weatherViewHolder(ItemTempBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: weatherViewHolder, position: Int) {
        val weather = getItem(position)
        holder.bind(weather)
    }


}

