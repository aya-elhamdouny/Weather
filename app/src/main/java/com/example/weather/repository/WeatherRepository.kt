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


@SuppressLint("TimberArgCount")
fun formatGeoLocation() : String{
    var lat = geoLocatoin.lat
    var long =geoLocatoin.long
    var comma = ","
    var query : String = ""
      query = lat.toString().plus("").plus(comma).plus(long.toString())
    return query
}





    suspend fun getForecast() =
        RetroftitBuilder.api.getForecast("alexandria" , days)
    
    suspend fun refreshData(){
        withContext(Dispatchers.IO){
            database.weatherDao.insertCurrentWeather(RetroftitBuilder.api.getForecast( "alexandria", days).current)
            database.weatherDao.insertLocation(RetroftitBuilder.api.getForecast("alexandria" , days).location)
            database.weatherDao.insertForecast(RetroftitBuilder.api.getForecast("alexandria" , days).forecast.forecastday)
            database.weatherDao.insertHour(RetroftitBuilder.api.getForecast("alexandria", days).forecast.forecastday[0].hour)
        }
    }

    fun setCoordinaties(lat : Double, long : Double) : String{
        var cityName:String =""
        var countryName = ""
        val geoCoder = Geocoder(App.app.applicationContext, Locale.getDefault())
        val Adress = geoCoder.getFromLocation(lat,long,3)
        cityName = Adress.get(0).locality
        countryName = Adress.get(0).countryName
        //Log.d("Debug:","Your City: " + cityName + " ; your Country " + countryName)
        return cityName

   }

}


