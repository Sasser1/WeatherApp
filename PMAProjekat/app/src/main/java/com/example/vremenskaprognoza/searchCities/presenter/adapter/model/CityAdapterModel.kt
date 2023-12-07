package com.example.vremenskaprognoza.searchCities.presenter.adapter.model

import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity
import com.example.vremenskaprognoza.searchCities.domain.model.CityResult
import com.example.vremenskaprognoza.searchCities.presenter.adapter.CitiesAdapter
import com.example.vremenskaprognoza.util.NetworkResult

/*
 *  Model for City Adapter
 */
data class CityAdapterModel(
    val cityName: String,
    val cityInfo: String,
    val cityEntity: CityEntity,
    val onCityListener: CitiesAdapter.OnCityListener,
    val cityResult: NetworkResult<CityResult>
)