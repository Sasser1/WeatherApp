package com.example.vremenskaprognoza.searchCities.data.repository

import com.example.vremenskaprognoza.searchCities.data.dataSource.GeoApi
import com.example.vremenskaprognoza.searchCities.domain.model.CityResult
import com.example.vremenskaprognoza.searchCities.domain.repository.NetworkCityRepository
import javax.inject.Inject

/*
 * Repository for getting cities from network
 */
class NetworkCityRepositoryImpl @Inject constructor(
    private val geoApi: GeoApi
) :  NetworkCityRepository {

    override suspend fun getCities(name: String): List<CityResult> {
        return geoApi.searchCity(name).cityResults
    }
}