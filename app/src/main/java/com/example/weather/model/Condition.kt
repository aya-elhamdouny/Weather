package com.example.weather.model

import java.io.Serializable

data class Condition constructor(
    val code: Int,
    val icon: String,
    val text: String
): Serializable