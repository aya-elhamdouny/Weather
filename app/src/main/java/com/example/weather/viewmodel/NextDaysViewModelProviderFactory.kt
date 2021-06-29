package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.model.Forecast
import com.example.weather.model.weather
import com.example.weather.repository.DatabaseRepository
import com.example.weather.repository.WeatherRepository

class NextDaysViewModelProviderFactory (private val weatherRepository: WeatherRepository
                                        , private val databaseRepository: DatabaseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NextDaysViewModel(weatherRepository , databaseRepository) as T
    }
}