package com.example.weather.model

import androidx.room.Embedded
import java.io.Serializable

data class Forecast(
    @Embedded
    val forecastday: List<Forecastday>
): Serializable