package com.example.weather.model

import androidx.room.Embedded
import androidx.room.Entity
import java.io.Serializable



data class Forecast constructor(
    @Embedded
    val forecastday: List<Forecastday>
): Serializable