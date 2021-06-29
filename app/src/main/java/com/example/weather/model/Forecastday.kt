package com.example.weather.model

import androidx.room.*
import java.io.Serializable

data class Forecastday constructor(

    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
): Serializable
