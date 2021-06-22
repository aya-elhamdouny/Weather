package com.example.weather.repository

import com.example.weather.api.RetroftitBuilder

class WeatherRepository {

    suspend fun getForecast() =
        RetroftitBuilder.api.getForecast()
}