package com.example.weather.model

import androidx.room.Entity
import androidx.room.Ignore
import com.squareup.moshi.Json
import java.io.Serializable


//@Entity(tableName = "location")
data class Location(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
): Serializable
