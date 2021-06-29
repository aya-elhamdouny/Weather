package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Forecastday
import com.example.weather.model.weather

class DetailViewModel(forecastday: Forecastday, application: Application) : ViewModel() {

    private val _selectedDay = MutableLiveData<Forecastday>()
    val selectedDay : LiveData<Forecastday>
       get() = _selectedDay

    init {
        _selectedDay.value = forecastday
    }
}