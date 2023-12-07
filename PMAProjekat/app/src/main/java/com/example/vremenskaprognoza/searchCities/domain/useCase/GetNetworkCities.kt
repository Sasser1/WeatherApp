package com.example.vremenskaprognoza.searchCities.domain.useCase

import android.content.Context
import android.widget.Toast
import com.example.vremenskaprognoza.R
import com.example.vremenskaprognoza.WeatherApplication
import com.example.vremenskaprognoza.searchCities.domain.model.CityGeo
import com.example.vremenskaprognoza.searchCities.domain.repository.NetworkCityRepository
import com.example.vremenskaprognoza.util.NetworkResult

/*
 *  Get Cities from network by city name
 */
class GetNetworkCities(
    private val repository: NetworkCityRepository,
    private val context: Context
) {

    suspend operator fun invoke(name: String): NetworkResult<CityGeo> {
        if (WeatherApplication.hasNetwork() == false){
            Toast.makeText(context,  context.getString(R.string.no_network_connection), Toast.LENGTH_SHORT).show()
        }
        return try {
            NetworkResult.Success(CityGeo(repository.getCities(name)))
        } catch(exception: Exception){
            NetworkResult.Error(exception = exception)
        }
    }
}