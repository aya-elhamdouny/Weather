

package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.model.Forecastday
import com.example.weather.model.weather

class DetailViewModelProviderFactory(private val forecastday: Forecastday,
                                     private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(forecastday , application) as T
    }
}