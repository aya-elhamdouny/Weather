package com.example.weather.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.weather

class DetailViewModel( weather: weather , application: Application) : ViewModel() {

    private val _selectedDay = MutableLiveData<weather>()
    val selectedDay : LiveData<weather>
       get() = _selectedDay

    init {
        _selectedDay.value = weather
    }
}