package com.example.Forecastday.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FragmentNextDaysBinding
import com.example.weather.databinding.ItemDayBinding
import com.example.weather.model.Forecastday
import com.example.weather.model.weather

class NextDayAdapter(val onClickListener: OnClickListener) : ListAdapter<Forecastday, NextDayAdapter.viewHolder>(DiffCallback) {


    class viewHolder(private var binding : ItemDayBinding)  : RecyclerView.ViewHolder(binding.root){
        fun bind(forecast: Forecastday){
           binding.forecast = forecast
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
        return viewHolder(ItemDayBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class OnClickListener(val clickListener: (forecastday : Forecastday) -> Unit) {
        fun onClick(forecastday : Forecastday) = clickListener(forecastday)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val forecast = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(forecast)
        }
        holder.bind(forecast)
    }

}