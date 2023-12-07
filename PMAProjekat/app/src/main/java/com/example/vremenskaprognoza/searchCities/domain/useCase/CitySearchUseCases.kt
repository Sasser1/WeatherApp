package com.example.vremenskaprognoza.searchCities.domain.useCase

/*
*   Use Cases for City Search fragment
**/
data class CitySearchUseCases(
    val getDatabaseCities: GetDatabaseCities,
    val getNetworkCities: GetNetworkCities,
    val getFavoriteCities: GetFavoriteCities,
    val insertCities: InsertCities,
    val updateCity: UpdateCity
)