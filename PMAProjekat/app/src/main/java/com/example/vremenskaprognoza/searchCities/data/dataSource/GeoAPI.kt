package com.example.vremenskaprognoza.searchCities.data.dataSource

import com.example.vremenskaprognoza.searchCities.domain.model.CityGeo
import retrofit2.http.GET
import retrofit2.http.Query

/*
*   API for getting Geo by city name
**/
interface GeoApi {

    @GET("search?count=20&language=en&format=json")
    suspend fun searchCity(@Query(value = "name") name: String): CityGeo
}