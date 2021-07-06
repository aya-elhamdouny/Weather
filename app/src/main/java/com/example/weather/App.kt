package com.example.weather

import android.app.Application
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import androidx.work.*
import com.example.weather.Work.RefreshDataWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class App : Application() {


    private val applicationScope = CoroutineScope(Dispatchers.Default)

    companion object {
        lateinit var app: App
        var ip = 0
        const val WORK_NAME = "RefreshDataWorker"

    }


    private fun delayinit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }


    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val reapeatingRequest =
            PeriodicWorkRequestBuilder<RefreshDataWork>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(baseContext).enqueueUniquePeriodicWork(
            RefreshDataWork.WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, reapeatingRequest
        )


    }


    override fun onCreate() {
        super.onCreate()
        app = this
        delayinit()


        try {
            val wifiManager = app.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val ipAddress: Int = wifiManager.connectionInfo.ipAddress
            ip = ipAddress
            Log.d("ADebugTag", "ipaddress: " + ipAddress)


        } catch (e: Exception) {
            val _status: String
            _status = "Failure: ${e.message}"
        }


    }


}