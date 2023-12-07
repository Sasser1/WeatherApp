package com.example.vremenskaprognoza.searchCities.domain.useCase

import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity
import com.example.vremenskaprognoza.searchCities.domain.repository.DatabaseCityRepository

/*
 *  Insert Cities to database
 */
class InsertCities(
    private val repository: DatabaseCityRepository
) {

    suspend operator fun invoke(cityEntities: List<CityEntity>) {
        repository.insertCities(cityEntities)
    }
}