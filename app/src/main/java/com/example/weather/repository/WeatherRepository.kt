package com.example.weather.repository

import android.location.Geocoder
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
import timber.log.Timber
import java.util.*

class WeatherRepository(val database: WeatherDatabase)  {

    var days : Int = 14

   val forecastdayResult  :LiveData<List<Forecastday>> =  database.weatherDao.getForecast()

   val currentResult : LiveData<Current> = database.weatherDao.getCurrentWeather()

    val locationResult : LiveData<Location> = database.weatherDao.getLocation()

    val hourResult :  LiveData<List<Hour>> = database.weatherDao.getHours()


    //val hour :  LiveData<List<Hour>> = database.weatherDao.getHour()

    var lat: Double = 0.0
    var long: Double = 0.0

    suspend fun getForecast() =
        RetroftitBuilder.api.getForecast("alexandria" , days)



   /* suspend fun getCountryName()=
            IPRetrofitBuilder.api.getCountryname(App.ip ).country_name

*/


    suspend fun refreshData(){
        withContext(Dispatchers.IO){
            database.weatherDao.insertCurrentWeather(RetroftitBuilder.api.getForecast("alexandria" , days).current)
            database.weatherDao.insertLocation(RetroftitBuilder.api.getForecast("alexandria" , days).location)
            database.weatherDao.insertForecast(RetroftitBuilder.api.getForecast("alexandria" , days).forecast.forecastday)
            database.weatherDao.insertHour(RetroftitBuilder.api.getForecast("alexandria", days).forecast.forecastday[0].hour)
        }
    }

    fun setCoordinaties(lat : Double, long : Double) : String{
        var cityName:String = ""
        var countryName = ""
        val geoCoder = Geocoder(App.app.applicationContext, Locale.getDefault())
        val Adress = geoCoder.getFromLocation(lat,long,3)
        cityName = Adress.get(0).locality
        countryName = Adress.get(0).countryName
        //Log.d("Debug:","Your City: " + cityName + " ; your Country " + countryName)
        return cityName

   }

}