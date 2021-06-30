package com.example.weather

import android.app.Application
import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import com.example.weather.database.WeatherDatabase

class App : Application()
{

    companion object{
        lateinit var app : App
        var ip = 0

    }

    override fun onCreate() {
        super.onCreate()
        app = this


        try {
            val wifiManager = app.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val ipAddress: Int = wifiManager.connectionInfo.ipAddress
            ip = ipAddress
            Log.d("ADebugTag", "ipaddress: " + ipAddress)


        } catch (e: Exception) {
            val _status: String
            _status = "Failure: ${e.message}"
        }


    }



}