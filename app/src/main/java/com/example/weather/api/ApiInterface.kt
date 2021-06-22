package com.example.weather.api


import com.example.weather.model.weather
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //https://weatherapi-com.p.rapidapi.com/forecast.json?q=London&days=3
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") Country : String = "alexandria"
    ): weather



}