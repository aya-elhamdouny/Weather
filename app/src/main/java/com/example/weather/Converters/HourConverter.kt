package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Day
import com.example.weather.model.Forecastday
import com.example.weather.model.Hour
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HourConverter {


    @TypeConverter
    fun HourToString(hour: Hour) : String?{
        return Gson().toJson(hour)
    }

    @TypeConverter
    fun StringToHour(string: String) : Hour? {
        return Gson().fromJson(string , Hour::class.java)
    }



    @TypeConverter
    fun HourListToString(list: List<Hour?>?): String? {
        return Gson().toJson(list)
    }


    @TypeConverter
    fun stringToHourList(json: String?): List<Hour>? {

        val list = object : TypeToken<List<Hour?>?>() {}.type
        return Gson().fromJson(json , list )
    }
}