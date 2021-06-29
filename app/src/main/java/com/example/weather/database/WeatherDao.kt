package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.model.Current
import com.example.weather.model.Forecastday
import com.example.weather.model.Location
import com.example.weather.model.weather

@Dao
interface WeatherDao {

/*

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)


    @Query("select * from location ")
    fun getLocation() : LiveData<Location>
*/




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(current: Current)


    @Query("select * from currentWeather ")
    fun getCurrentWeather() : LiveData<Current>


/*

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecastday: List<Forecastday>)


    @Query("select * from location ")
    fun getForecast() : LiveData<Forecastday>*/
}