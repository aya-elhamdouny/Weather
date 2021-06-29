package com.example.weather.model

import androidx.room.Embedded
import androidx.room.Entity
import java.io.Serializable


@Entity(tableName = "weather")
data class weather(
    @Embedded
    val current: Current,
    @Embedded
    val forecast: Forecast,
    @Embedded
    val location: Location
) : Serializable