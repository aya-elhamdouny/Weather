package com.example.weather

import android.app.Application
import android.net.wifi.WifiManager
import android.os.Build
import androidx.work.*
import com.example.weather.Work.RefreshDataWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.net.InetAddress
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.TimeUnit


class App : Application() {


    private val applicationScope = CoroutineScope(Dispatchers.Default)

    companion object {
        lateinit var app: App
        var ip = 0
        var lat = 0.0
        var long = 0.0
        var ipString = ""
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
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        delayinit()

        try {
            val wifiManager = app.getSystemService(WIFI_SERVICE) as WifiManager
            val ipAddress: Int = wifiManager.connectionInfo.ipAddress
            ip = ipAddress

            ipString = InetAddress.getByAddress(
                ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ip).array()
            ).getHostAddress()

            Timber.d("ipaddress: %s", ipString)
            Timber.d("ipaddresstring: %s", ipAddress)

        } catch (e: Exception) {
            val _status: String
            _status = "Failure: ${e.message}"
        }


    }


}