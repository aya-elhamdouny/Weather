package com.example.weather.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class RetroftitBuilder {
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
            retrofit.create(ApiInterface::class.java)
        }

        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder().addHeader(
                        "x-rapidapi-key",
                        "104d22342fmsha8dbb0cce23a095p1965f2jsn0a3d72b57796"
                    ).addHeader("x-rapidapi-host", "weatherapi-com.p.rapidapi.com").build()

                chain.proceed(request)
            }
            this.addInterceptor(logging)
        } .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()

    }

}