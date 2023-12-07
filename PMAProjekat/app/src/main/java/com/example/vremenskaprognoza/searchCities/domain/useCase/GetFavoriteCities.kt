package com.example.vremenskaprognoza.searchCities.domain.useCase

import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity
import com.example.vremenskaprognoza.searchCities.domain.repository.DatabaseCityRepository

/*
 *  Get Favorite Cities from database
 */
class GetFavoriteCities(
    private val repository: DatabaseCityRepository
) {

    suspend operator fun invoke(): List<CityEntity> {
        return repository.getFavoriteCities()
    }
}