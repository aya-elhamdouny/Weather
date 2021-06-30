package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    private val _navigateToSelectedProperty = MutableLiveData<Current>()
    val navigateToSelectedProperty : LiveData<Current>
        get() =_navigateToSelectedProperty



    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    init {
        getWeather()


    }

    private fun getWeather() {
        viewModelScope.launch {

                val listResult = weatherRepository.getForecast()
                _response.postValue(listResult)
                Log.d("Time hi",listResult.forecast.forecastday[2].hour[0].time)
                _hour.value = listResult.forecast.forecastday[2].hour
                _forecast.value = listResult.forecast.forecastday[0]

            
            //insert in database
            weatherRepository.insertCurrentWeather(listResult.current)
            weatherRepository.insertLocation(listResult.location)
            weatherRepository.insertforecast(listResult.forecast.forecastday)

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun displayPropertyDetails(weather: weather) {
        _navigateToSelectedProperty.value = weather.current
    }



    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}