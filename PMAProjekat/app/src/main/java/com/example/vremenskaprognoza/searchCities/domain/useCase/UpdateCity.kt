package com.example.vremenskaprognoza.searchCities.domain.useCase

import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity
import com.example.vremenskaprognoza.searchCities.domain.repository.DatabaseCityRepository

/*
 *  Update Cities from database
 */
class UpdateCity(
    private val repository: DatabaseCityRepository
) {

    suspend operator fun invoke(cityEntity: CityEntity) {
        repository.updateCity(cityEntity)
    }
}