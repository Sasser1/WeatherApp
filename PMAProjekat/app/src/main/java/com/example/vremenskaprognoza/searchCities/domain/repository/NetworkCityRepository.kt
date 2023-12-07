package com.example.vremenskaprognoza.searchCities.domain.repository

import com.example.vremenskaprognoza.searchCities.domain.model.CityResult

interface NetworkCityRepository {
    suspend fun getCities(name: String): List<CityResult>
}