
package com.example.weather.model
import java.io.Serializable



data class weather (
    val current: Current,
    val forecast: Forecast,
    val location: Location
) : Serializable
