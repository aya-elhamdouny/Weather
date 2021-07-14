package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.weather.model.*
import com.example.weather.repository.WeatherRepository
import com.example.weather.ui.geoLocatoin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.*

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



    private var viewModelJob = Job()

    val current = weatherRepository.currentResult
    val location = weatherRepository.locationResult
    //val listHour = weatherRepository.hour
   /* init {
        getWeather()
        Timber.d("from viwmodel1")

    }*/

     fun getWeather(query : String) {
        viewModelScope.launch {
                weatherRepository.refreshData(query)
           val listResult = weatherRepository.getForecast(query)
           _hour.value = listResult.forecast.forecastday[0].hour

        }
    }

    fun sendtoRepo(lat : Double , long: Double) {
     Timber.d("from viwmodel2")
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}