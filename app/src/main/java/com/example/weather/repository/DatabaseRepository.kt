package com.example.weather.repository

import com.example.weather.database.WeatherDatabase
import com.example.weather.model.Current
import com.example.weather.model.Forecastday
import com.example.weather.model.Location
import com.example.weather.model.weather

class DatabaseRepository(val database: WeatherDatabase) {




suspend fun insertCurrentWwather(weather: weather) =
    database.weatherDao.insertCurrentWeather(weather)


   /* suspend fun insertLocation(location: Location) =
        database.weatherDao.insertLocation(location)


    suspend fun insertforecast(forecastday: List<Forecastday>) =
        database.weatherDao.insertForecast(forecastday)

*/







}