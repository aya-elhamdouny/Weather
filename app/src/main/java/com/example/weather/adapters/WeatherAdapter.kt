package com.example.weather.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.ItemTempBinding
import com.example.weather.model.Hour


class WeatherAdapter : ListAdapter<  Hour, WeatherAdapter.weatherViewHolder>(DiffCallback){

    class weatherViewHolder(private var binding : ItemTempBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hour: Hour){
            binding.hour = hour
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<Hour>() {

        override fun areItemsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hour, newItem: Hour): Boolean {
            return  oldItem.is_day == newItem.is_day
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): weatherViewHolder {
        return weatherViewHolder(ItemTempBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: weatherViewHolder, position: Int) {
        val hour = getItem(position)
        holder.bind(hour)
    }


}

