package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Forecast
import com.example.weather.model.Forecastday
import com.example.weather.model.weather
import com.example.weather.repository.DatabaseRepository
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.launch

class NextDaysViewModel(val weatherRepository: WeatherRepository ) : ViewModel() {

    private val _forecast = MutableLiveData<List<Forecastday>>()
    val forecast : LiveData<List<Forecastday>>
        get() = _forecast

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _navigateToSelectedProperty =MutableLiveData<Forecastday>()
    val navigateToSelectedProperty : LiveData<Forecastday>
        get() = _navigateToSelectedProperty


    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
            try {
                val listResult = weatherRepository.getday()
                _forecast.value = listResult
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }

    }


    fun displayPropertyDetails(forecastday: Forecastday) {
       _navigateToSelectedProperty.value = forecastday
    }



    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }















}