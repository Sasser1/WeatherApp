package com.example.vremenskaprognoza.searchCities.data.repository

import com.example.vremenskaprognoza.searchCities.data.dataSource.CityDao
import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity
import com.example.vremenskaprognoza.searchCities.domain.repository.DatabaseCityRepository
import javax.inject.Inject

/*
 * Repository for getting cities from database
 */
class DatabaseCityRepositoryImpl @Inject constructor(
    private val cityDao: CityDao
) : DatabaseCityRepository {

    override suspend fun getCities(cityName: String): List<CityEntity> {
        return cityDao.getCities(cityName)
    }

    override suspend fun getFavoriteCities(): List<CityEntity> {
        return cityDao.getFavoriteCities()
    }

    override suspend fun insertCities(cityEntities: List<CityEntity>) {
        return cityDao.insertCities(cityEntities)
    }

    override suspend fun updateCity(cityEntity: CityEntity) {
        cityDao.updateCity(cityEntity)
    }

}