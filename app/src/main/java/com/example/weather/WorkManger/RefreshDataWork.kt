package com.example.weather.Work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weather.App
import com.example.weather.database.WeatherDatabase
import com.example.weather.repository.WeatherRepository
import retrofit2.HttpException

class RefreshDataWork(appContext : Context , params : WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object{
        const val WORK_NAME = "RefreshDataWorker"    }


    override suspend fun doWork(): Result {
        val repository = WeatherRepository(WeatherDatabase.getDatabase(App.app))
        return try {
            repository.refreshData()
            Result.success()
        }catch (e: HttpException){
            Result.retry()
        }
    }


}
