package com.example.weather.model

import com.squareup.moshi.Json
import java.io.Serializable

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