package com.example.weather.locationservices

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.location.LocationManagerCompat
import androidx.lifecycle.LiveData
import com.example.weather.model.Postion
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.*

class LocationLiveData(context: Context)  : LiveData<Postion>() {


    private var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    companion object{
        //To receive location update with FusedLocationProviderClient we need to do location request
        val locationRequest : LocationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval= 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }


    @SuppressLint("ServiceCast")
    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }

    //will be invoked when we have location update from FusedLocationProviderClient
    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult?) {
           p0?: return
            // return the last saved location
            var lat = p0.lastLocation.latitude
            val long = p0.lastLocation.longitude
            for(location in p0.locations)
            {
                setLocationData(location)
            }
        }

    }



   //to call fused.updates

    @SuppressLint("MissingPermission")
    private fun startLocation(){
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest, locationCallback, null
        )

    }




    //mapping location data with postion model
    private fun setLocationData(location : Location){
        value = Postion(
            longitude = location.longitude,
            latitude = location.latitude
        )
    }


    //trigger lifecycle owner
    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener {
                it?.also {
                    setLocationData(it)
                }
            }
        startLocation()
    }

    override fun onInactive() {
        super.onInactive()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }






}