package com.example.weather.model

import androidx.room.*
import java.io.Serializable


@Entity
data class Forecastday constructor(
    @PrimaryKey
    val date: String,
    val date_epoch: Int,
    val day: Day,
    @ColumnInfo(name = "hourList")
    val hour: List<Hour>
): Serializable


