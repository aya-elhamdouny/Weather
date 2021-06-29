package com.example.weather.database

import android.content.Context
import android.location.Location
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.model.Current
import com.example.weather.model.Forecastday


@Database(entities = [Location::class , Forecastday::class , Current::class] , version = 1)
abstract class WeatherDatabase    : RoomDatabase(){

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