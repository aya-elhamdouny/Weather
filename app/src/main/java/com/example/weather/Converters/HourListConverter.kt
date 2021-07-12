package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Hour
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HourListConverter {

    @TypeConverter
    fun HourListToString(list: List<Hour?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToHourList(json: String?): List<Hour>? {

        val list = object : TypeToken<List<Hour?>?>() {}.type
        return Gson().fromJson(json, list)
    }

}