package com.example.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Current
import com.example.weather.model.Forecast
import com.example.weather.model.Hour
import com.example.weather.model.weather
import com.example.weather.repository.DatabaseRepository
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel(val weatherRepository: WeatherRepository, val databaseRepository: DatabaseRepository) : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status


    private val _response = MutableLiveData<weather>()
    val response : LiveData<weather>
        get() = _response

    private val _hour = MutableLiveData<List<Hour>>()
    val hour : LiveData<List<Hour>>
        get() = _hour

    private val _forecast = MutableLiveData<Forecast>()
    val forecast : LiveData<Forecast>
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

            try {
                val listResult = weatherRepository.getForecast()
                _response.value = listResult
                _hour.value = listResult.forecast.forecastday[3].hour
                _forecast.value = listResult.forecast
                databaseRepository.insertCurrentWwather(listResult.current)
               /* databaseRepository.insertLocation(listResult.location)
                databaseRepository.insertforecast(listResult.forecast.forecastday)*/
                } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
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