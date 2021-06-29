
package com.example.weather.model
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.Expose
import java.io.Serializable



data class weather (
    val current: Current,
    val forecast: Forecast,
    val location: Location
) : Serializable
