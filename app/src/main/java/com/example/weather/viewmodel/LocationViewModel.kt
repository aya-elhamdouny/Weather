package com.example.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weather.App
import com.example.weather.locationservices.LocationLiveData

class LocationViewModel(app: App) : ViewModel()  {

    private val locationData =  LocationLiveData(app)

    val lat = locationData.value?.latitude
    val long = locationData.value?.longitude

    fun getLocation() =
        locationData
}