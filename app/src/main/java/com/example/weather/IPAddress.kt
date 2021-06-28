package com.example.weather

import android.content.Context
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class IPAddress : AppCompatActivity() {

     public  var ipAdress : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun setIp(int: Int) : Int{
        return int.also { ipAdress = it }
    }

}