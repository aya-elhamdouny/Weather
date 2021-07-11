package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.weather.model.*
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status


    private val _response = MutableLiveData<weather>()
    val response : LiveData<weather>
        get() = _response


    private val _hour = MutableLiveData<List<Hour>>()
    val hour : LiveData<List<Hour>>
        get() = _hour


    private val _forecast = MutableLiveData<Forecastday>()
    val forecast : LiveData<Forecastday>
        get() = _forecast


    var latitude : Double = 0.0
    var longitude : Double = 0.0



    private var viewModelJob = Job()

    val current = weatherRepository.currentResult
    val location = weatherRepository.locationResult
    val listHour = weatherRepository.hourResult



    init {
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
                weatherRepository.refreshData()
           val listResult = weatherRepository.getForecast()
           _hour.value = listResult.forecast.forecastday[0].hour


        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}