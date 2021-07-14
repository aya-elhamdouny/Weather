package com.example.weather.repository

import android.annotation.SuppressLint
import android.location.Geocoder
import androidx.lifecycle.LiveData
import com.example.weather.App
import com.example.weather.api.RetroftitBuilder
import com.example.weather.database.WeatherDatabase
import com.example.weather.model.*
import com.example.weather.ui.geoLocatoin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*

class WeatherRepository(val database: WeatherDatabase , val app: App)  {

    var days : Int = 14

   val forecastdayResult  :LiveData<List<Forecastday>> =  database.weatherDao.getForecast()

   val currentResult : LiveData<Current> = database.weatherDao.getCurrentWeather()

    val locationResult : LiveData<Location> = database.weatherDao.getLocation()

    val hourResult :  LiveData<List<Hour>> = database.weatherDao.getHours()

    //val hour :  LiveData<List<Hour>> = database.weatherDao.getHour()



fun formatGeoLocation(lat: Double , long : Double) : String{

     var comma = ","
     var q : String = ""
     q = lat.toString().plus("").plus(comma).plus(long.toString())
     return q
}

    suspend fun getForecast(query : String) =
        RetroftitBuilder.api.getForecast(query , days)

    suspend fun refreshData(query : String){
        withContext(Dispatchers.IO){
            database.weatherDao.insertCurrentWeather(RetroftitBuilder.api.getForecast( query, days).current)
            database.weatherDao.insertLocation(RetroftitBuilder.api.getForecast(query , days).location)
            database.weatherDao.insertForecast(RetroftitBuilder.api.getForecast(query , days).forecast.forecastday)
            database.weatherDao.insertHour(RetroftitBuilder.api.getForecast(query, days).forecast.forecastday[0].hour)
        }
    }



}


