package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Forecastday
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ForecastConverter {


    @TypeConverter
    fun stringToForecastday(json: String?): List<Forecastday>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Forecastday?>?>() {}.type
        return gson.fromJson<List<Forecastday>>(json, type)
    }


    @TypeConverter
    fun forecastdayToString(list: List<Forecastday?>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Forecastday?>?>() {}.type
        return gson.toJson(list, type)
    }
}