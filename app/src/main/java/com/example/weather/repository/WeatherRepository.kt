package com.example.weather.repository

import com.example.weather.App
import com.example.weather.addresapi.IPRetrofitBuilder
import com.example.weather.api.RetroftitBuilder
import com.example.weather.database.WeatherDatabase
import com.example.weather.model.Current
import com.example.weather.model.Forecastday
import com.example.weather.model.Location

class WeatherRepository(val database: WeatherDatabase)  {

    var days : Int = 14

    suspend fun getForecast() =
        RetroftitBuilder.api.getForecast(getCountryName() , days)

    suspend fun getday()=
        RetroftitBuilder.api.getForecast(getCountryName() , days).forecast.forecastday

    suspend fun getCountryName()=
            IPRetrofitBuilder.api.getCountryname(App.ip).country_name

    suspend fun getHour()=
        RetroftitBuilder.api.getForecast(getCountryName(), days).forecast.forecastday[3].hour



    suspend fun insertCurrentWeather(current: Current) =
        database.weatherDao.insertCurrentWeather(current)


    suspend fun insertLocation(location: Location) =
        database.weatherDao.insertLocation(location)

    suspend fun insertforecast(forecastday: List<Forecastday>) =
        database.weatherDao.insertForecast(forecastday)







}