package com.example.weather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.weather.App
import com.example.weather.addresapi.IPRetrofitBuilder
import com.example.weather.api.RetroftitBuilder
import com.example.weather.database.WeatherDatabase
import com.example.weather.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherRepository(val database: WeatherDatabase)  {

    var days : Int = 14


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

//    val result  : LiveData<Current> = Transformations.map(database.weatherDao.getCurrentWeather()){
//
//    }

    suspend fun getForecast() =
        RetroftitBuilder.api.getForecast(getCountryName() , days)

    suspend fun getday()=
        RetroftitBuilder.api.getForecast(getCountryName() , days).forecast.forecastday

    suspend fun getCountryName()=
            IPRetrofitBuilder.api.getCountryname(App.ip).country_name


    suspend fun refreshData(){
        withContext(Dispatchers.IO){
            database.weatherDao.insertCurrentWeather(RetroftitBuilder.api.getForecast(getCountryName() , days).current)
            database.weatherDao.insertLocation(RetroftitBuilder.api.getForecast(getCountryName(), days).location)
            database.weatherDao.insertForecast(RetroftitBuilder.api.getForecast(getCountryName(), days).forecast.forecastday)
        }
    }

















}