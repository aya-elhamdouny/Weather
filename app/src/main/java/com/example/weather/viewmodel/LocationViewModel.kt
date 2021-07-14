package com.example.weather.viewmodel

import android.annotation.SuppressLint
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weather.App
import com.example.weather.App.Companion.app
import com.example.weather.locationservices.LocationLiveData
import timber.log.Timber
import java.util.*

@SuppressLint("TimberArgCount")
class LocationViewModel(app: App) : ViewModel()  {

    lateinit var city : LiveData<String>



init {

}


}