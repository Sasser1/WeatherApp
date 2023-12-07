package com.example.vremenskaprognoza.searchCities.presenter.viewModel.model

import com.example.vremenskaprognoza.searchCities.domain.model.CityEntity

sealed interface CitySearchEvent{
    data class GetCities(val cityName: String, val isTyping: Boolean): CitySearchEvent
    data class ToggleFavorite(val cityEntity: CityEntity): CitySearchEvent
}