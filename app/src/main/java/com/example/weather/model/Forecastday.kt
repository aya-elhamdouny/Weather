package com.example.weather.model

import androidx.room.*
import java.io.Serializable
@Entity
data class Forecastday constructor(
    @PrimaryKey
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
): Serializable
/*

fun List<Forecastday>.asDomainModel(): List<Forecastday> {

    return  map {
        Forecastday(
            date = it.date,
            date_epoch = it.date_epoch,
            day = it.day,
            hour = it.hour

        )
    }
}
*/

