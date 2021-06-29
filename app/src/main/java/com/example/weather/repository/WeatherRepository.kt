package com.example.weather.repository

import android.app.Application
import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import com.example.weather.App
import com.example.weather.IPAddress
import com.example.weather.addresapi.IPRetrofitBuilder
import com.example.weather.api.RetroftitBuilder
import kotlin.coroutines.coroutineContext

class WeatherRepository()  {


    suspend fun getForecast() =
        RetroftitBuilder.api.getForecast(getCountryName())


    suspend fun getday()=
        RetroftitBuilder.api.getForecast("alexandria").forecast.forecastday

        suspend fun getCountryName()=
            IPRetrofitBuilder.api.getCountryname(App.ip).country_name




}