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



    private val _Location = MutableLiveData<Location>()
    val location : LiveData<Location>
        get() = _Location


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        //getfrom data base and put it into el initlized val aly fo2
        getWeather()
    }

    private fun getWeather() {
        viewModelScope.launch {
                weatherRepository.refreshData()
                val listResult = weatherRepository.getForecast()
                _response.postValue(listResult)
                Log.d("Time hi",listResult.forecast.forecastday[2].hour[0].time)
                _hour.value = listResult.forecast.forecastday[2].hour
                _forecast.value = listResult.forecast.forecastday[0]

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}