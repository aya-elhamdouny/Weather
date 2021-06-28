package com.example.weather

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val ipAddress: Int = wifiManager.connectionInfo.ipAddress
            Log.d("ADebugTag", "ipaddress: " + ipAddress)

        } catch (e: Exception) {
            val _status: String
            _status = "Failure: ${e.message}"
        }



    }




}