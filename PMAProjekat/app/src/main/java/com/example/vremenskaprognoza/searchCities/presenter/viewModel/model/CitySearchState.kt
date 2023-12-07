package com.example.vremenskaprognoza.searchCities.presenter.viewModel.model

import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity

data class CitySearchState(
    val cities: List<CityEntity> = listOf(),
    val currentCityName: String = "",
    val uiState: CitySearchUIState = CitySearchUIState.Success
)
