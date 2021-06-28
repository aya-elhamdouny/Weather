package com.example.weather.model

import java.io.Serializable

data class Forecastday(
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
): Serializable