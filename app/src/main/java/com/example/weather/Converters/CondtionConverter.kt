package com.example.weather.Converters

import androidx.room.TypeConverter
import com.example.weather.model.Condition
import com.google.gson.Gson


class CondtionConverter {


    @TypeConverter
    fun condtionToString(condtion : Condition) : String{
         return Gson().toJson(condtion)
    }

    @TypeConverter
    fun StringToCondtion(string: String) : Condition{
        return Gson().fromJson(string , Condition::class.java)
    }



}