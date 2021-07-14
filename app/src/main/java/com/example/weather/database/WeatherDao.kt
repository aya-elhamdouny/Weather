package com.example.weather.database

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weather.model.Current
import com.example.weather.model.Forecastday
import com.example.weather.model.Hour
import com.example.weather.model.Location

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)

    @Query("select * from location ")
    fun getLocation() : LiveData<Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(current: Current)

    @Query("select * from currentWeather ")
    fun getCurrentWeather() : LiveData<Current>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecastday: List<Forecastday>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHour(hours :List<Hour> )


    @Query("SELECT * FROM hour")
    fun getHours() : LiveData<List<Hour>>

    @Query("select * from forecastday ")
    fun getForecast() : LiveData<List<Forecastday>>


    @Query("SELECT hourList FROM forecastday")
    fun getHour():  LiveData<List<Hour>>
}
