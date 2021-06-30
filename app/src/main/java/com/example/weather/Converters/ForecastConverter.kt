package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Forecastday
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ForecastConverter {



    @TypeConverter
    fun forecastDayToString(list: List<Forecastday?>?): String? {
        return Gson().toJson(list)
    }


    @TypeConverter
    fun stringToForecastDay(json: String?): List<Forecastday>? {

        val list = object : TypeToken<List<Forecastday?>?>() {}.type
        return Gson().fromJson(json , list )
    }



}