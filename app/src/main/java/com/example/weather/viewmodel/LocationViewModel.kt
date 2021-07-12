package com.example.weather.viewmodel

import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weather.App
import com.example.weather.App.Companion.app
import com.example.weather.locationservices.LocationLiveData
import timber.log.Timber
import java.util.*

class LocationViewModel(app: App) : ViewModel()  {

    private val locationData =  LocationLiveData(app)
    var name : String = ""

    fun getLocation() =
        locationData
init {
    getLocation()

}


}