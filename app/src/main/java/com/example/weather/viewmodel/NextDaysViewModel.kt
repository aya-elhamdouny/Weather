package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Forecast
import com.example.weather.model.weather
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.launch

class NextDaysViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    private val _forecast = MutableLiveData<Forecast>()
    val forecast : LiveData<Forecast>
        get() = _forecast

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _navigateToSelectedProperty = MutableLiveData<Forecast>()
    val navigateToSelectedProperty : LiveData<Forecast>
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


    fun displayPropertyDetails(weather: weather) {
       _navigateToSelectedProperty.value = weather.forecast
    }



    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }















}