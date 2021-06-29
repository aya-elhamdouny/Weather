package com.example.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.repository.DatabaseRepository
import com.example.weather.repository.WeatherRepository

class WeatherViewModelProviderFactory(val weatherRepository :  WeatherRepository
, val databaseRepository: DatabaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository, databaseRepository) as T
    }
}