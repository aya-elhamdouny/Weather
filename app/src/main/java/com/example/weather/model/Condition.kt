package com.example.weather.model

import java.io.Serializable

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)