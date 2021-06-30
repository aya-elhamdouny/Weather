package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Condition
import com.example.weather.model.Day
import com.google.gson.Gson

class DayConverter {


    @TypeConverter
    fun DayToString(day : Day) : String{
        return Gson().toJson(day)
    }

    @TypeConverter
    fun StringToDay(string: String) : Day {
        return Gson().fromJson(string , Day::class.java)
    }
}