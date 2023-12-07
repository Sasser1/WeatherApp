package com.example.vremenskaprognoza.searchCities.domain.useCase

import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity
import com.example.vremenskaprognoza.searchCities.domain.repository.DatabaseCityRepository

/*
 *  Get Cities from database by city name
 */
class GetDatabaseCities(
    private val repository: DatabaseCityRepository
) {

    suspend operator fun invoke(cityName: String): List<CityEntity> {
        return repository.getCities(cityName)
    }
}