package com.example.weather.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "currentWeather")
data class Current(
    val cloud: Int,
    val condition: Condition,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val humidity: Int,
    val is_day: Int,
    @PrimaryKey
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val temp_f: Double,
    val uv: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double
): Serializable

fun List<Current>.asDomainModel() : List<Current> {
    return map {
        Current(
            cloud = it.cloud,
            condition = it.condition,
            feelslike_c = it.feelslike_c,
            feelslike_f = it.feelslike_f,
            gust_mph = it.gust_mph,
            gust_kph = it.gust_kph,
            humidity = it.humidity,
            is_day = it.is_day,
            last_updated = it.last_updated,
            last_updated_epoch = it.last_updated_epoch,
            precip_mm = it.precip_mm,
            precip_in = it.precip_in,
            pressure_in = it.pressure_in,
            pressure_mb = it.pressure_mb,
            temp_c = it.temp_c,
            temp_f = it.temp_f,
            uv = it.uv,
            vis_km = it.vis_km,
            vis_miles = it.vis_miles,
            wind_degree = it.wind_degree,
            wind_dir = it.wind_dir,
            wind_kph = it.wind_kph,
            wind_mph = it.wind_mph
        )
    }

}
