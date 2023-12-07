package com.example.vremenskaprognoza.searchCities.domain.repository

import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity

interface DatabaseCityRepository {
    suspend fun getCities(cityName: String): List<CityEntity>

    suspend fun getFavoriteCities(): List<CityEntity>

    suspend fun insertCities(cityEntities: List<CityEntity>)

    suspend fun updateCity(cityEntity: CityEntity)
}