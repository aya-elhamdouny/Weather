package com.example.weather.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "location")
data class Location constructor(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    @PrimaryKey
    val region: String,
    val tz_id: String,

): Serializable

fun List<Location>.asDomainModel() : List<Location>{
    return map{
        Location(
            country = it.country,
            lat = it.lat,
            localtime = it.localtime,
            localtime_epoch = it.localtime_epoch,
            lon = it.lon,
            name= it.name,
            region = it.region,
            tz_id = it.tz_id

        )
    }
}


