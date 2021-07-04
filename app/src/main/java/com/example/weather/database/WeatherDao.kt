package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weather.model.Current
import com.example.weather.model.Forecastday
import com.example.weather.model.Location
import com.example.weather.model.weather

@Dao
interface WeatherDao {

    @Update
    suspend fun insertLocation(location: Location)

    @Query("select * from location ")
    fun getLocation() : LiveData<Location>

    @Update
    suspend fun insertCurrentWeather(current: Current)

    @Query("select * from currentWeather ")
    fun getCurrentWeather() : LiveData<Current>

    
    @Update
    suspend fun insertForecast(forecastday: List<Forecastday>)


    @Query("select * from forecastday ")
    fun getForecast() : LiveData<Forecastday>

}
