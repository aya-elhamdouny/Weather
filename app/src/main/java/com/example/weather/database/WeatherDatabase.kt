package com.example.weather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.Converters.*
import com.example.weather.model.Current
import com.example.weather.model.Forecastday
import com.example.weather.model.Hour
import com.example.weather.model.Location


@Database(entities = [Location::class , Forecastday::class , Current::class , Hour::class] ,
                    version = 1 , exportSchema = false)

@TypeConverters(CondtionConverter::class , DayConverter::class , ForecastConverter::class,
                ForecasydayConverter::class , HourConverter::class )
abstract class WeatherDatabase: RoomDatabase(){

    abstract val weatherDao : WeatherDao

     companion object{
         @Volatile
         private lateinit var INSTANCE: WeatherDatabase

         fun getDatabase(context: Context): WeatherDatabase {
             synchronized(WeatherDatabase::class.java) {
                 if (!::INSTANCE.isInitialized) {
                     INSTANCE = Room.databaseBuilder(
                         context.applicationContext,
                         WeatherDatabase::class.java,
                         "weather.db"
                     ).fallbackToDestructiveMigration()
                         .build()
                 }
             }
             return INSTANCE
         }
     }
}
