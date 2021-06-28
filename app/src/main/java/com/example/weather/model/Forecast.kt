package com.example.weather.model

import java.io.Serializable

data class Forecast(
    val forecastday: List<Forecastday>
): Serializable