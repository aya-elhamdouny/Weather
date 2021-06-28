package com.example.Forecast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentNextDaysBinding
import com.example.weather.model.Forecast
import com.example.weather.model.weather

class NextDayAdapter(val onClickListener: OnClickListener) : ListAdapter<Forecast, NextDayAdapter.viewHolder>(DiffCallback) {


    class viewHolder(private var binding: FragmentNextDaysBinding)  : RecyclerView.ViewHolder(binding.root){

        fun bind(forecast: Forecast){
            binding.viewmodel = forecast
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Forecast>(){
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return    oldItem.forecastday== newItem.forecastday
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(FragmentNextDaysBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class OnClickListener(val clickListener: (weather: weather) -> Unit) {
        fun onClick(weather: weather) = clickListener(weather)
    }
}