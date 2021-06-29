package com.example.weather.model

import java.io.Serializable



data class Forecast constructor(
    val forecastday: List<Forecastday>
): Serializable