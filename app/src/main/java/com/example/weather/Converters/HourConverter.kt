package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Hour
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken

class HourConverter {

    @TypeConverter
    fun HourToString(hour: Hour) : String?{
    return Gson().toJson(hour) }

    @TypeConverter
    fun StringToHour(string: String) : Hour? {
        return Gson().fromJson(string, Hour::class.java)
    }



/*
    @TypeConverter
    fun jsonArrayToHour(jsonArray: JsonArray) : List<Hour>? {
        return Gson().fromJson(jsonArray, object : TypeToken<List<Hour?>?>() {}.type)
    }

    @TypeConverter
    fun tryyy(jsonArray: JsonArray) : ArrayList<Hour>? {
           return Gson().fromJson(jsonArray.toString(), object : TypeToken<List<Hour?>?>() {}.type)
    }*/




  }
