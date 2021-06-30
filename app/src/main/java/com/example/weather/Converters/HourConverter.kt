package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Day
import com.example.weather.model.Hour
import com.google.gson.Gson

class HourConverter {


    @TypeConverter
    fun HourToString(hour: Hour) : String?{
        return Gson().toJson(hour)
    }

    @TypeConverter
    fun StringToHour(string: String) : Hour? {
        return Gson().fromJson(string , Hour::class.java)
    }
}