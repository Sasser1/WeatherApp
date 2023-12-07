package com.example.vremenskaprognoza.weather.domain.model

data class ClickedWeatherItem(
    val time: String?,
    val weatherCode: Int?,
    val tempMax: Double?,
    val tempMin: Double?,
    val windSpeed: Double?,
    val humidity: Long?,
    val apparentTemp: Double?,
    val precipitation: Double?
)
