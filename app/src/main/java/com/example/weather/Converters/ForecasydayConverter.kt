package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Day
import com.example.weather.model.Forecastday
import com.google.gson.Gson

class ForecasydayConverter {


    @TypeConverter
    fun forecastdayToString(forecastday: Forecastday) : String{
        return Gson().toJson(forecastday)
    }

    @TypeConverter
    fun StringToforecastday(string: String) : Forecastday {
        return Gson().fromJson(string , Forecastday::class.java)
    }
}