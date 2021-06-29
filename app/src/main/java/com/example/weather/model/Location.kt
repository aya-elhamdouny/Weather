package com.example.weather.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


///@Entity(tableName = "location")
data class Location(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    @PrimaryKey
    val region: String,
    val tz_id: String,

): Serializable{
}
