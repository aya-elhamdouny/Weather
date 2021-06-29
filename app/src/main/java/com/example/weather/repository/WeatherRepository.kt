package com.example.weather.repository

import com.example.weather.App
import com.example.weather.addresapi.IPRetrofitBuilder
import com.example.weather.api.RetroftitBuilder

class WeatherRepository()  {

    var days : Int = 3

    suspend fun getForecast() =
        RetroftitBuilder.api.getForecast(getCountryName() , days)


    suspend fun getday()=
        RetroftitBuilder.api.getForecast(getCountryName() , days).forecast.forecastday

        suspend fun getCountryName()=
            IPRetrofitBuilder.api.getCountryname(App.ip).country_name

    suspend fun getHour()=
        RetroftitBuilder.api.getForecast(getCountryName(), days).forecast.forecastday[3].hour





}