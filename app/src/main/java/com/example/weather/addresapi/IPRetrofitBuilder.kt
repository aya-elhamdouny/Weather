package com.example.weather.addresapi

import com.example.weather.api.ApiInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class IPRetrofitBuilder {

        companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://weatherapi-com.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        val api by lazy {
            retrofit.create(IPapiInterface::class.java)
        }


        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder().addHeader(
                        "x-rapidapi-key",
                        "104d22342fmsha8dbb0cce23a095p1965f2jsn0a3d72b57796"
                    ).addHeader("x-rapidapi-host", "weatherapi-com.p.rapidapi.com").build()

                chain.proceed(request)
            }
        } .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()

    }

    }