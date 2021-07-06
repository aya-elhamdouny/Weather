package com.example.weather.addresapi

import com.example.weather.model.ip
import retrofit2.http.GET
import retrofit2.http.Query

interface IPapiInterface {


    //https://weatherapi-com.p.rapidapi.com/ip.json?q=268566538
    @GET("ip.json")
    suspend fun getCountryname(
        @Query("q") ip : Int
    ): ip

}