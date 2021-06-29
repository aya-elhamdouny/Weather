package com.example.weather.model

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "forecast")
data class Forecastday constructor(
    @PrimaryKey
    val date: String,
    val date_epoch: Int,
    @Embedded
    val day: Day,
    @Embedded
    val hour: List<Hour>
): Serializable
