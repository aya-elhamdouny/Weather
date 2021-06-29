package com.example.weather.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import java.io.Serializable


//@Entity(tableName = "forecast")
data class Forecastday(
    val date: String,
    val date_epoch: Int,
    @Embedded
    val day: Day,
    @Embedded
    val hour: List<Hour>
): Serializable
