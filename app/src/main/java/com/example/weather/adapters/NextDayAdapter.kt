package com.example.Forecastday.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentNextDaysBinding
import com.example.weather.model.Forecastday
import com.example.weather.model.weather

class NextDayAdapter(val onClickListener: OnClickListener) : ListAdapter<Forecastday, NextDayAdapter.viewHolder>(DiffCallback) {


    class viewHolder(private var binding: FragmentNextDaysBinding)  : RecyclerView.ViewHolder(binding.root){

        fun bind(forecast: Forecastday){
           binding.viewmodel.forecast
           binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Forecastday>(){
        override fun areItemsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Forecastday, newItem: Forecastday): Boolean {
            return    oldItem.date== newItem.date
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